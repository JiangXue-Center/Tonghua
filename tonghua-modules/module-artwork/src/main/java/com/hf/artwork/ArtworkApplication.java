package com.hf.artwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ArtworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtworkApplication.class);
    }

}
