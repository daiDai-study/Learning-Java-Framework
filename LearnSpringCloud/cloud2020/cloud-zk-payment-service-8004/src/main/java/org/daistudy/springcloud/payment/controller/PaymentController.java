package org.daistudy.springcloud.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.daistudy.springcloud.common.entity.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/info")
    public String info(){
        String str = "spring cloud with zookeeper:" + serverPort + "\t" + UUID.randomUUID().toString();
        log.info(str);
        return str;
    }
}
