package com.atguigu.routing;

import com.atguigu.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    private static final String EXCHANGE_NAME = "direct_exchange";
    private static final String QUEUE_NAME1 = "direct_queue1";
    private static final String QUEUE_NAME2 = "direct_queue2";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true,false,null);

        channel.queueDeclare(QUEUE_NAME1, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME2, true, false, false, null);

        channel.queueBind(QUEUE_NAME1, EXCHANGE_NAME, "error");
        channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, "info");
        channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, "waring");
        channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, "error");

        String message = "日志信息：张三调用了delete方法.错误了,日志级别error";

        channel.basicPublish(EXCHANGE_NAME,"waring",null,message.getBytes());

        channel.close();
        connection.close();
    }
}
