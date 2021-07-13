package org.daistudy.springcloud.payment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("org.daistudy.springcloud.payment.mapper")
public class EurekaPayment8002Application {
    public static void main(String[] args) {
        SpringApplication.run(EurekaPayment8002Application.class, args);
    }
}
