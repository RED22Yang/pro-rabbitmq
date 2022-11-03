package com.atguigu.listener.topic;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener7 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("ConsumerListener7" + new String(message.getBody()));
    }
}
