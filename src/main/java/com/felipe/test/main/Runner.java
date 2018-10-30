package com.felipe.test.main;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner{

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {


        IntStream.range(0, 1000)
                .parallel()
                .forEach(value -> {
                    System.out.println("Sending message..."+value);
                    rabbitTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!"+value);
                    rabbitTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ1!"+value);
                });

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
