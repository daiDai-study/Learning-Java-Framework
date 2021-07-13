package org.daistudy.springcloud.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitmqProduce8801Application {
    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitmqProduce8801Application.class, args);
    }
}
