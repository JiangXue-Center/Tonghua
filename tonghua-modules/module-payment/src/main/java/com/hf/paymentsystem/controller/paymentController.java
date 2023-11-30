package com.hf.paymentsystem.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.hf.apisystem.api.RemoteOrderService;
import com.hf.cache.service.RedisService;
import com.hf.core.model.dto.OrderPayInfo;
import com.hf.paymentsystem.config.AlipayConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.hf.cache.constants.RedisConstant.ORDER_PAY_INFO_KEY;

@RestController
@RequestMapping("/pay")
public class paymentController {

    @Autowired
    private AlipayConfig config;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RemoteOrderService remoteOrderService;


    @PostMapping("/alipay")
    public void alipay(HttpServletResponse response, String orderNumber) throws IOException, AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(
                config.getGatewayUrl(), config.getAppId(),
                config.getAppPrivateKey(), config.getFormat(),
                config.getCharset(), config.getAlipayPublicKey(),
                config.getSignType()
        );
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        alipayTradePagePayRequest.setReturnUrl(config.getReturnUrl());
        alipayTradePagePayRequest.setNotifyUrl(config.getNotifyUrl());

        String orderPayInfoStr = redisService.getCacheObject(ORDER_PAY_INFO_KEY + orderNumber);
//        String s = "{\"out_trade_no\":\"${out_trade_no}\","
//                + "\"total_amount\":\"${total_amount}\","
//                + "\"subject\":\"${subject}\","
//                + "\"body\":\"${body}\","
//                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";
        OrderPayInfo orderPayInfo = JSONUtil.toBean(orderPayInfoStr, OrderPayInfo.class);
        JSONObject bizContent = new JSONObject();
        bizContent.set("out_trade_no", orderPayInfo.getOut_trade_no());
        bizContent.set("total_amount", orderPayInfo.getTotal_amount());
        bizContent.set("subject", orderPayInfo.getSubject());
        bizContent.set("product_code", "FAST_INSTANT_TRADE_PAY");
//        String s2 = STR."""
//                {
//                    "out_trade_no":"\{orderPayInfo.getOut_trade_no()}",
//                    "total_amount":"\{orderPayInfo.getTotal_amount()}",
//                    "subject":"\{orderPayInfo.getSubject()}",
//                    "product_code":"FAST_INSTANT_TRADE_PAY"
//                }
//                """;
        System.out.println(bizContent);
        alipayTradePagePayRequest.setBizContent(bizContent.toString());
        String formInfo = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
        response.setContentType("text/html;charset=" + config.getCharset());
        response.getWriter().write(formInfo);
        response.getWriter().flush();
        response.getWriter().close();
    }

    @GetMapping("/notify")
    public String notify(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> params = new HashMap<>();
        for (String name : parameterMap.keySet()) {
            String[] values = parameterMap.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            valueStr = new String(valueStr.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, config.getAlipayPublicKey(),
                config.getCharset(), config.getSignType());
        String result = "failure";
        if (signVerified) {
            String outTradeNo = params.get("out_trade_no");
            String tradeStatus = params.get("trade_status");
            if (tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")) {
                //修改订单状态
                result = remoteOrderService.updateOrderStatus(outTradeNo);
            }
        }
        return result;
    }

    @GetMapping("/return")
    private String returnUrl() {
        return "go2PaySuccess";
    }

}
