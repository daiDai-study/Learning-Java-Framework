package org.daistudy.mq.rabbitmq.producer.helloworld;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main {

    private final static String QUEUE_NAME = "helloworld";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        // 设置mq的连接信息
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        // 创建连接
        // 创建通道
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()){
            // 通道声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // 发送消息
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("publish: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
