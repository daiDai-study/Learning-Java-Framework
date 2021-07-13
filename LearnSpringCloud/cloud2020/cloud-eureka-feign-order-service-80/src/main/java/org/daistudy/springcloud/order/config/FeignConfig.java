package org.daistudy.springcloud.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.rmi.runtime.Log;

@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLever(){
        return Logger.Level.FULL;
    }
}
