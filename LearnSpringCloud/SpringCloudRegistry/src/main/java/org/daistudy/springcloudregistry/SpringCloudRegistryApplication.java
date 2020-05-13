package org.daistudy.springcloudregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // 启用注册中心服务器，Eureka 启动的时候，自己既是服务端，又是客户端
public class SpringCloudRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudRegistryApplication.class, args);
    }

}
