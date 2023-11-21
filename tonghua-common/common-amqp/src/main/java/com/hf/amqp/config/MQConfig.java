package com.hf.amqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.hf.amqp.constants.MQConstants.*;

@Configuration
public class MQConfig {

    // 定义交换机
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(ORDER_INVENTORY_EXCHANGE);
    }

    // 定义队列
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_INVENTORY_QUEUE);
    }

    // 将队列绑定到交换机
    @Bean
    public Binding binding(Queue orderQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(orderQueue).to(directExchange).with(SUBMIT_ORDER);
    }

}
