package com.atguigu.listener.fanout;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener3 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("ConsumerListener3 " + new String(message.getBody()));
    }

}
