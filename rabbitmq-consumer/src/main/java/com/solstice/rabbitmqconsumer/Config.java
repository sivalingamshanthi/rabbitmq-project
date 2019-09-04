package com.solstice.rabbitmqconsumer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public DirectExchange getExchange(){
        return new DirectExchange("eventExchange");
    }

    @Bean
    public Queue queue() {
        return new Queue("orderServiceQueue");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("someKey");
    }

    @Bean
    public EventConsumer eventReceiver() {
        return new EventConsumer();
    }
}
