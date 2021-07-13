package org.daistudy.springcloud.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosProvider9101Application {
    public static void main(String[] args) {
        SpringApplication.run(NacosProvider9101Application.class, args);
    }
}
