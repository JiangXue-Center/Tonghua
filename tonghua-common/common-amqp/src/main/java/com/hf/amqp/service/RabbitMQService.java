package com.hf.amqp.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Map;

public class RabbitMQService {

    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.send(new Message(message.getBytes()));
    }

    public <T> void convertAndSend(String exchange, String routingKey, Map<String, T> map) {
        rabbitTemplate.convertAndSend(exchange, routingKey, map);
    }

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
