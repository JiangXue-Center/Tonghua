package com.hf.artwork.config;

import com.hf.minio.service.MinIOService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({com.hf.minio.config.MinIOConfig.class, MinIOService.class})
public class MinIOConfig {
}
