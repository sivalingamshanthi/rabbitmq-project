package com.solstice.rabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class EventConsumer {

    @RabbitListener(queues = "orderServiceQueue")
    public void receive(String message){
        System.out.println("Received message " + message);
    }
}
