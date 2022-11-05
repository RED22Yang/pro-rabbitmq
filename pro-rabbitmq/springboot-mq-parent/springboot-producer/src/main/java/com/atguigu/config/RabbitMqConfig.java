package com.atguigu.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;



@SpringBootConfiguration
public class RabbitMqConfig {

    @Bean
    public Queue queue1(){
        return QueueBuilder.durable("springboot_test_queue").build();
    }

    @Bean
    public Queue queue2(){
        return QueueBuilder.durable("springboot_test2_queue").build();
    }

    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.fanoutExchange("springboot_test_exchange").durable(true).build();
    }

    @Bean
    public Binding binding(Queue queue1 , Exchange exchange){
        return BindingBuilder.bind(queue1).to(exchange).with("test.1").noargs();
    }

    @Bean
    public Binding binding2(Queue queue2, Exchange exchange){
        return BindingBuilder.bind(queue2).to(exchange).with("test.1").noargs();
    }



}
