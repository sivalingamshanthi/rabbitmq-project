package com.solstice.rabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.concurrent.TimeUnit;

public class EventConsumer {

    @RabbitListener(queues = "orderServiceQueue")
    public String receive(String message){
        System.out.println("Received message " + message);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "send this back";
    }
}
