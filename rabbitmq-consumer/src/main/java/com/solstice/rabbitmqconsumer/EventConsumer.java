package com.solstice.rabbitmqconsumer;

import com.solstice.rabbitmq.CustomMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class EventConsumer {

    @RabbitListener(queues = "orderServiceQueue")
    public void receive(CustomMessage message){
        System.out.println("Received message " + message.getA());
    }
}
