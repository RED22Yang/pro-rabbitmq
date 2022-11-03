package com.atguigu.topic;

import com.atguigu.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    private static final String EXCHANGE_NAME = "topic_exchange";
    private static final String QUEUE_NAME1 = "topic_queue1";
    private static final String QUEUE_NAME2 = "topic_queue2";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME1, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME2, true, false, false, null);
        channel.queueBind(QUEUE_NAME1, EXCHANGE_NAME, "adc.*");
        channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, "adc.#");

        String message = "topic test";
        channel.basicPublish(EXCHANGE_NAME,"adc.a.a",null,message.getBytes());

        channel.close();
        connection.close();
    }

}
