package com.hf.artwork;

import com.hf.cache.configure.RedisConfig;
import com.hf.data.config.DruidDataSourceConfig;
import com.hf.minio.config.MinIOConfig;
import com.hf.minio.service.MinIOService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import({MinIOConfig.class,
        MinIOService.class,
        DruidDataSourceConfig.class,
        RedisConfig.class
})
public class ArtworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtworkApplication.class);
    }

}
