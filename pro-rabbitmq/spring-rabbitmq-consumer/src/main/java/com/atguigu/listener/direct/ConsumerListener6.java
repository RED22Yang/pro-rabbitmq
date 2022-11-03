package com.atguigu.listener.direct;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener6 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("ConsumerListener6" + new String(message.getBody()));
    }

}
