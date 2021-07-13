package org.daistudy.springcloud.provider.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

@Service
public class HystrixCircuitBreakServiceImpl implements HystrixCircuitBreakService {

    @HystrixCommand(fallbackMethod = "randomFallback", commandProperties = {
        @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
    })
    @Override
    public String random(Long id) {
        if(id < 0){
            throw new RuntimeException("id 不能为负数");
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + ": " + uuid + "。id 为" + id;
    }

    public String randomFallback(Long id){
        return Thread.currentThread().getName() + ": id 不能为负数。 id 为" + id;
    }
}
