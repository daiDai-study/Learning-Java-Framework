package org.daistudy.springcloud.nacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class ConsumerController {

    @Value("${service-url.nacos-provider}")
    private String providerServiceUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/port")
    public String port(){
        return restTemplate.getForObject(providerServiceUrl + "/provider/port", String.class);
    }

}
