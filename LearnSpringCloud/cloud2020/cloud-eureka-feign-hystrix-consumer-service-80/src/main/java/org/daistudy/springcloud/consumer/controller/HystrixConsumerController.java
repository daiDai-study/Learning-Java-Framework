package org.daistudy.springcloud.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.daistudy.springcloud.consumer.api.HystrixProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/consumer/hystrix")
@DefaultProperties(defaultFallback = "handlerGlobal")
public class HystrixConsumerController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private HystrixProviderService hystrixProviderService;

    @GetMapping("/fallback/ok")
    public String ok() {
        String ok = hystrixProviderService.ok();
        log.info("{}-{}", serverPort, ok);
        return ok;
    }

    // 服务端运行时间3s,服务降级超时时间为5s，会通过
    // 客户端调用服务端，运行时间为2s,服务降级超时时间（默认为1s），
    // 如果设置小于1s，则会在超时时间之后服务降级，配置生效；如果设置大于1s，则还是会在默认超时时间之后服务降级，配置未生效
    // execution.isolation.thread.timeoutInMilliseconds 设置超过默认超时间，整个服务降级配置未生效
    @GetMapping("/fallback/timeout")
    @HystrixCommand(fallbackMethod = "handler", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
    })
    public String timeout() {
        String timeout = hystrixProviderService.timeout(); // 3s
        log.info("{}-{}", serverPort, timeout);
        return timeout;
    }

    @GetMapping("/fallback/exception")
//    @HystrixCommand()
    public String exception() {
//        int a = 10/0;
        String exception = hystrixProviderService.exception();
        log.info("{}-{}", serverPort, exception);
        return exception;
    }

    public String handler(){
        return serverPort + "-系统运行超时或运行异常，/(ㄒoㄒ)/~~" + Thread.currentThread().getName();
    }

    public String handlerGlobal(){
        return "全局：" + serverPort + "-系统运行超时或运行异常，/(ㄒoㄒ)/~~" + Thread.currentThread().getName();
    }
}
