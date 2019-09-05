package com.solstice.rabbitmqproducer;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.RemoteEndpoint;

@RestController
public class ProducerController {

    //@Autowired
    //private RabbitTemplate rabbitTemplate;

    @Autowired
    private AsyncRabbitTemplate rabbitTemplate;

    @Autowired
    Exchange exchange;

    @PostMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        //rabbitTemplate.convertAndSend(exchange.getName(), "someKey", message);
        ListenableFuture<String> future = rabbitTemplate.convertSendAndReceive(exchange.getName(), "someKey", message);

        future.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("failure");
            }

            @Override
            public void onSuccess(String result) {
                System.out.println("success");
            }
        });

        System.out.println("done");

        return "success";
    }

    @Bean
    public Exchange eventExchange() {
        return new DirectExchange("eventExchange");
    }

    @Bean
    public Queue replyQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public AsyncRabbitTemplate asyncTemplate(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setRoutingKey("someKey");
        rabbitTemplate.setReplyAddress(replyQueue().getName());
        return new AsyncRabbitTemplate(rabbitTemplate);
    }
}
