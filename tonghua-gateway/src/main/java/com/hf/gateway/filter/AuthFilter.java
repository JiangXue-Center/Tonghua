package com.hf.gateway.filter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.hf.cache.service.RedisService;
import com.hf.core.exception.AuthException;
import com.hf.core.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.hf.cache.constants.RedisConstant.LOGIN_TOKEN_KEY;
import static com.hf.cache.constants.RedisConstant.LOGIN_TOKEN_TTL;
import static com.hf.core.constant.Constants.ACCESS_TOKEN;
import static com.hf.core.enums.ExceptionEnums.TOKEN_VERIFY_ERROR;


@Component
public class AuthFilter implements GlobalFilter, Ordered {

    public static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);


    private static final Set<String> EXCLUDE_PATH = ImmutableSet.of(
            "/auth/login",
            "/auth/code",
            "/auth/check",
            "/auth/register",
            "/pay/alipay",
            "/pay/notify",
            "/pay/return"
    );

    private static final String SOCKJS_PREFIX = "/communication";


    @Autowired
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return Mono.just(exchange)
                .map(ServerWebExchange::getRequest)
                .flatMap(serverHttpRequest -> {
                    String path = serverHttpRequest.getPath().toString();
                    logger.info("请求的路径path: {}", path);
                    if (path.startsWith(SOCKJS_PREFIX)) {
                        logger.info("path: {}, 请求为sockjs, 无需校验身份", path);
                        return chain.filter(exchange);
                    }
                    if (EXCLUDE_PATH.stream().anyMatch(path::contains)) {
                        logger.info("path: {} 无需登录 ", path);
                        return chain.filter(exchange);
                    }

                    List<String> list = serverHttpRequest.getHeaders().get(ACCESS_TOKEN);
                    if (CollectionUtil.isEmpty(list)) {
                        return this.buildLoginResponse(exchange);
                    }

                    String accessToken = Iterables.getOnlyElement(list);
                    if (!JwtUtil.verifyToken(accessToken)) {
                        throw new AuthException(TOKEN_VERIFY_ERROR);
                    }
                    DecodedJWT tokenInfo = JwtUtil.getTokenInfo(accessToken);
                    String id = tokenInfo.getClaim("id").asString();
                    StringBuilder builder = new StringBuilder();
                    builder.append(LOGIN_TOKEN_KEY);
                    builder.append(id);
                    String key = builder.toString();
                    String redisToken = redisService.getCacheObject(key);
                    if (StrUtil.hasBlank(redisToken)) {
                        return this.buildLoginResponse(exchange);
                    }
                    if (!StrUtil.equals(accessToken, redisToken)) {
                        return this.buildLoginResponse(exchange);
                    }
                    redisService.expire(key, LOGIN_TOKEN_TTL, TimeUnit.HOURS);
                    return chain.filter(exchange);
                });
    }

    @Override
    public int getOrder() {
        return 10002;
    }

    private Mono<Void> buildLoginResponse(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("code", "401");
        map.put("message", "无效token");
        String jsonStr = JSONUtil.toJsonStr(map);
        byte[] bytes = jsonStr.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        response.setStatusCode(HttpStatus.OK);
        return response.writeWith(Flux.just(buffer));
    }
}
