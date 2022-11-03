package com.atguigu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class test {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test01(){
        String message = "hello rabbitmq";
        rabbitTemplate.convertAndSend("","spring_simple_queue",message);
    }

    @Test
    public void test02(){
        for (int i = 0; i < 10; i++) {
            String message = "hello rabbitmq" + i;
            rabbitTemplate.convertAndSend("","spring_work_queue",message);
        }
    }

    @Test
    public void test03(){
        String message = "hello rabbitmq";
        rabbitTemplate.convertAndSend("spring_fanout_exchange","",message);
    }

    @Test
    public void test04(){
        String message = "hello rabbitmq";
        rabbitTemplate.convertAndSend("spring_direct_exchange","b",message);
    }

    @Test
    public void test05(){
        String message = "hello rabbitmq";
        rabbitTemplate.convertAndSend("spring_topic_exchange","a.a.abc",message+1);
        rabbitTemplate.convertAndSend("spring_topic_exchange","abc.a",message+2);
        rabbitTemplate.convertAndSend("spring_topic_exchange","a.abc.a",message+3);
    }

}
