package org.daistudy.springcloud.demo.member.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.daistudy.springcloud.demo.member.client.BookClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class MemberController {
    @Autowired
    private Environment environment;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private BookClient bookClient;

    @GetMapping("port")
    public String getPort(){
        return environment.getProperty("server.port");
    }

    @GetMapping("test")
    @HystrixCommand
    public String test(){
        final String json = restTemplate.getForObject("http://book/port", String.class);
        return json;
    }

    public String fallback(){
        return "系统正忙，请稍后再试111";
    }

    public String defaultFallback(){
        return "系统正忙，请稍后再试222";
    }

    @GetMapping("testFeign")
    public String testFeign(){
        final String json = bookClient.getPort();
        return json;
    }
}
