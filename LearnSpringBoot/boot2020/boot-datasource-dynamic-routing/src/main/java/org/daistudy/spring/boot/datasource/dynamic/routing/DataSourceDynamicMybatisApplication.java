package org.daistudy.spring.boot.datasource.dynamic.routing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DataSourceDynamicMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataSourceDynamicMybatisApplication.class, args);
    }
}
