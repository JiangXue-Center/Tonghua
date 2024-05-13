package com.hf.communication.config;

import com.hf.minio.config.MinIOConfig;
import com.hf.minio.service.MinIOService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MinIOConfig.class, MinIOService.class})
public class ExternalConfig {
}
