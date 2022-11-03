package com.atguigu.listener.topic;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener8 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("ConsumerListener8" + new String(message.getBody()));
    }
}
