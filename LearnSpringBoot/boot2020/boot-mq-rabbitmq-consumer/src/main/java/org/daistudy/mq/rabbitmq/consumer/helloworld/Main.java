package org.daistudy.mq.rabbitmq.consumer.helloworld;

import com.rabbitmq.client.*;
import org.daistudy.mq.rabbitmq.consumer.util.RabbitMQUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Main {

    private final static String QUEUE_NAME = "helloworld";

    public static void main(String[] args) throws IOException, TimeoutException {
        final ConnectionFactory factory = RabbitMQUtil.connectionFactory;

        // 创建连接
        Connection connection = factory.newConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 通道声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 定义接受消息后的处理方法
        Consumer consume = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                final String exchange = envelope.getExchange();
                final String routingKey = envelope.getRoutingKey();
                final String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("exchange = " + exchange + "\t routingKey = " + routingKey + "\t receive: " + message);
            }
        };

        // 接受消息
        channel.basicConsume(QUEUE_NAME, true, consume);
    }
}
