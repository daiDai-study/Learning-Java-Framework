package org.daistudy.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
@EnableConfigServer // 配置了EnableConfigServer注解，不配EnableEurekaClient注解也可以注册到注册中心
public class ConfigServer3344Application {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer3344Application.class, args);
    }
}
