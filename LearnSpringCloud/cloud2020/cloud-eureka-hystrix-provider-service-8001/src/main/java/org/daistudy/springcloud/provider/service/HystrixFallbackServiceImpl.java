package org.daistudy.springcloud.provider.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties(defaultFallback = "handlerGlobal")
public class HystrixFallbackServiceImpl implements HystrixFallbackService {
    @Override
    public String ok() {
        return "ok-系统运行正常，O(∩_∩)O。" + Thread.currentThread().getName();
    }

    @HystrixCommand(fallbackMethod = "timeoutHandler", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    @Override
    public String timeout() {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "timeout-系统运行正常，O(∩_∩)O。" + Thread.currentThread().getName();
    }

    public String timeoutHandler(){
        return "系统运行超时，/(ㄒoㄒ)/~~" + Thread.currentThread().getName();
    }

    @HystrixCommand()
    @Override
    public String exception() {
        int i = 1/0;
        return "exception-系统运行正常，O(∩_∩)O。" + Thread.currentThread().getName();
    }

    public String handlerGlobal(){
        return "全局：系统运行异常，/(ㄒoㄒ)/~~" + Thread.currentThread().getName();
    }
}
