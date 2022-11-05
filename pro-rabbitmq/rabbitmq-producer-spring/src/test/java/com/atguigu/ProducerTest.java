package com.atguigu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test(){

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println(correlationData);
                if (ack) {
                    System.out.println("连接交换机成功！！");
                }else {
                    System.out.println("错误消息:"+cause);
                }
            }
        });

        rabbitTemplate.setMandatory(true);//手动开启当前方法的回退模式

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {

            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

                System.out.println(new String(message.getBody()));
                System.out.println(replyCode);
                System.out.println(exchange);
                System.out.println(routingKey);

            }
        });

        String message = "投递可靠性测试";
        rabbitTemplate.convertAndSend("spring_confirm_exchange","confirm",message);
    }

    @Test
    public void test02(){
        String message = "ttl test";
        for (int i = 0; i < 11; i++) {
            rabbitTemplate.convertAndSend("spring_ttl_exchange","ttl",message + i);
        }
    }

    @Test
    public void test03(){
        String message = "dlx test";
        rabbitTemplate.convertAndSend("spring_dlx_exchange","dlx",message);
    }

    @Test
    public void test04(){
        String message = "dlx test";
        for (int i = 0; i < 21; i++) {
            rabbitTemplate.convertAndSend("spring_dlx_exchange","dlx",message + i);
        }
    }

    @Test
    public void test05(){
        String message = "dlx test";
        rabbitTemplate.convertAndSend("spring_dlx_exchange","dlx",message);
    }
}
