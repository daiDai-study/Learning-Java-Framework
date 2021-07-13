package org.daistudy.springcloud.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitmqConsumer8802Application {
    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitmqConsumer8802Application.class, args);
    }
}
