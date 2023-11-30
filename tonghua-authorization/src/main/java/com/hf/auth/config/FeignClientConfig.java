package com.hf.auth.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.hf.apisystem.api") // 指定Feign客户端接口所在的包
public class FeignClientConfig {
    // 可以留空，不需要定义其他Bean
}
