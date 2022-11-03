package com.atguigu.listener.direct;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener5 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("ConsumerListener5" + new String(message.getBody()));
    }

}
