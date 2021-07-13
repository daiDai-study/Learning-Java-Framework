package org.daistudy.mq.rabbitmq.consumer.util;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtil {

    public static ConnectionFactory connectionFactory;

    static{
        connectionFactory = new ConnectionFactory();
        // 设置mq的连接信息
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
    }
}
