package com.atguigu.listener.work;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener2 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("ConsumerListener2 "+new String(message.getBody()));
    }

}
