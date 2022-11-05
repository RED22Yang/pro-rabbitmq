package com.atguigu.mq.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConsumerListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message,Channel channel) throws IOException {

        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("deliveryTag:" +deliveryTag);
        System.out.println(new String(message.getBody()));
        try {
//            int a = 3 / 0 ;

            channel.basicAck(deliveryTag,true);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(deliveryTag,true,true);
        }
    }
}
