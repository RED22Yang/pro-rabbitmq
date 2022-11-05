package com.atguigu.mq.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener2 implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            System.out.println(new String(message.getBody()));
            int i = 3 / 0;
            channel.basicAck(deliveryTag,true);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(deliveryTag,true,false);
        }

    }


}
