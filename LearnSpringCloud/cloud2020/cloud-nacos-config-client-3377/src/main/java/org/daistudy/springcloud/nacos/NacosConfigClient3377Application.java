package org.daistudy.springcloud.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigClient3377Application {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClient3377Application.class, args);
    }
}