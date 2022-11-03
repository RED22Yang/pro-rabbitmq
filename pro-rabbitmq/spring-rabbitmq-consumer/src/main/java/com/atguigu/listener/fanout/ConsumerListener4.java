package com.atguigu.listener.fanout;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener4 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("ConsumerListener4 " + new String(message.getBody()));
    }

}
