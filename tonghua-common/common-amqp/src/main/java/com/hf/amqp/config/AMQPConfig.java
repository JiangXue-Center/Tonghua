package com.hf.amqp.config;

import com.hf.amqp.service.RabbitMQService;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties(prefix = "spring.rabbitmq")
public class AMQPConfig {

//    private String host;
//
//    private int port;
//
//    private String username;
//
//    private String password;
//
//    private String virtualHost;

    @Bean
    public RabbitMQService rabbitMQService(RabbitTemplate rabbitTemplate) {
        RabbitMQService rabbitMQService = new RabbitMQService();
        rabbitMQService.setRabbitTemplate(rabbitTemplate);
        return rabbitMQService;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // Additional RabbitTemplate configuration if needed
        return rabbitTemplate;
    }


}
