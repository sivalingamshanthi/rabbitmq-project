package com.solstice.rabbitmqproducer;

import com.solstice.rabbitmq.CustomMessage;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    Exchange exchange;

    @PostMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        CustomMessage m = new CustomMessage("a val", "b val");
        rabbitTemplate.convertAndSend(exchange.getName(), "someKey", m);
        return "success";
    }

    @Bean
    public Exchange eventExchange() {
        return new DirectExchange("eventExchange");
    }
}
