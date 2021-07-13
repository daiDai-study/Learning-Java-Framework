package org.daistudy.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
public class ConfigClient3355Application {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient3355Application.class, args);
    }
}
