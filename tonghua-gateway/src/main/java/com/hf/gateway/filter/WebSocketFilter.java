package com.hf.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

@Component
public class WebSocketFilter implements GlobalFilter, Ordered {

    public static final String DEFAULT_FILTER_PATH = "/ws/info";

    public static final String DEFAULT_FILTER_WEBSOCKET = "websocket";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String upgrade = exchange.getRequest().getHeaders().getUpgrade();
        URI requestUrl = exchange.getRequiredAttribute(GATEWAY_REQUEST_URL_ATTR);
        String scheme = requestUrl.getScheme();
        if (!scheme.equals("ws") && !scheme.equals("wss")) {
            return chain.filter(exchange);
        } else if (requestUrl.getPath().equals(DEFAULT_FILTER_PATH)) {
            String wsScheme = convertWs2Http(scheme);
            URI wsRequestUrl = UriComponentsBuilder.fromUri(requestUrl).scheme(wsScheme).build().toUri();
            exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, wsRequestUrl);
        } else if (requestUrl.getPath().indexOf(DEFAULT_FILTER_WEBSOCKET) < 0) {
            String wsScheme = convertWs2Http(scheme);
            URI wsRequestUrl = UriComponentsBuilder.fromUri(requestUrl).scheme(wsScheme).build().toUri();
            exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, wsRequestUrl);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 10001;
    }

    static String convertWs2Http(String scheme) {
        scheme = scheme.toLowerCase();
        return scheme.equals("ws") ? "http" : scheme.equals("wss") ? "https" : scheme;
    }
}
