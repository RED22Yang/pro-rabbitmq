package com.atguigu.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BootListener {

    @RabbitListener(queues = {"springboot_test_queue","springboot_test2_queue"})
    public void onMessage(Message message){
        System.out.println(new String(message.getBody()));
    }

}
