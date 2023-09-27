package com.hf.userplatform.config;

import com.hf.cache.configure.RedisConfig;
import com.hf.cache.service.RedisService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RedisService.class, RedisConfig.class})
public class RedisServiceConfig {
}
