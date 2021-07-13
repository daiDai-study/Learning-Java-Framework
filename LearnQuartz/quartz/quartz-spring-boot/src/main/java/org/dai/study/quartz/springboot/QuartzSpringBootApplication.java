package org.dai.study.quartz.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class QuartzSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuartzSpringBootApplication.class, args);
    }
}
