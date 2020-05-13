package org.daistudy.springcloudclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 启动时想 Eureka 服务端注册微服务信息
public class SpringCloudClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudClientApplication.class, args);
    }

}
