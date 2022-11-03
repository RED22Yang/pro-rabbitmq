package com.atguigu.work;

import com.atguigu.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        String QUEUE_NAME = "work_queue";
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        for (int i = 0; i < 10; i++) {
            String body = "hello rabbitmq" + i;
            channel.basicPublish("",QUEUE_NAME,null,body.getBytes());
        }
        channel.close();
        connection.close();
    }

}
