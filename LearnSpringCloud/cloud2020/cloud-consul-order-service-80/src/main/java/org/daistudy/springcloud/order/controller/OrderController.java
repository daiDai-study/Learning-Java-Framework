package org.daistudy.springcloud.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class OrderController {

    private final static String PAYMENT_URL = "http://cloud-payment-service/payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/info")
    public String info(){
        String str = restTemplate.getForObject(PAYMENT_URL + "/info", String.class);
        log.info("rest获取结果：{}", str);
        return str;
    }
}
