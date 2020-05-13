package org.daistudy.springcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

// Zuul路由的访问规则
// http://ip:port/application.name/...
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy // 启用Zuul路由服务
public class SpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

}
