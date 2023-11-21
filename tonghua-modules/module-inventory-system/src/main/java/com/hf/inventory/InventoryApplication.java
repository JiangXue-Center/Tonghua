package com.hf.inventory;

import com.hf.amqp.config.MQConfig;
import com.hf.cache.configure.RedisConfig;
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
        MQConfig.class,
        RedisConfig.class
})
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class);
    }
}
