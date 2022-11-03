package com.atguigu.fanout;

import com.atguigu.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    private static final String EXCHANGE_NAME = "fanout_exchange";
    private static final String QUEUE_NAME1 = "fanout_queue1";
    private static final String QUEUE_NAME2 = "fanout_queue2";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME1, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME2, true, false, false, null);

        //fanout  routingKey 设置为""
        channel.queueBind(QUEUE_NAME1, EXCHANGE_NAME, "");
        channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, "");

        String message = "fanout exchange1";
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());

        channel.close();
        connection.close();
    }
}
