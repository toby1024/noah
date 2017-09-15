package cn.skio.cdcservice.rabbit;

import cn.skio.cdcservice.CDCServiceApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Sender {

    @Value("${cscService.queueName}")
    public String queueName;

    private final RabbitTemplate rabbitTemplate;
    private final ConfigurableApplicationContext context;


    public Sender(RabbitTemplate rabbitTemplate, ConfigurableApplicationContext context) {
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    public void sendString(String message) throws Exception {
        System.out.println("Sending string message...");
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
