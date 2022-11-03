package com.atguigu.listener.work;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener1 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("ConsumerListener1 "+new String(message.getBody()));
    }

}
