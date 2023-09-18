package com.hf.auth.service.impl;

import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.hf.auth.service.SmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * description:
 *
 * @author huanghuiyuan
 * @date 2023-5-19 14:51
 */
@Component
public class SmsServiceImpl implements SmsService {

    @Value("${sms.signName}")
    private String signName;

    @Value("${sms.templateCode}")
    private String templateCode;

    @Resource
    private AsyncClient client;

    @Override
    public void sendMsg(String phone, String code) {
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName(signName)
                .templateCode(templateCode)
                .phoneNumbers(phone)
                .templateParam("{\"code\":\"" + code + "\"}")
                .build();

        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        try {
            System.out.println(response.get());
        } catch (InterruptedException | ExecutionException e) {
            //todo 换成自定义异常
            throw new RuntimeException(e);
        }
    }
}
