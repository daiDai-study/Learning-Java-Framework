package org.daistudy.springcloud.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ZKPayment8004Application {
    public static void main(String[] args) {
        SpringApplication.run(ZKPayment8004Application.class, args);
    }
}
