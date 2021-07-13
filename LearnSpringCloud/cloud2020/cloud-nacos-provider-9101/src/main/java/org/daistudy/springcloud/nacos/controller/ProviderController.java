package org.daistudy.springcloud.nacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
@Slf4j
public class ProviderController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/port")
    public String port(){
        String str = "nacos registry, server port: " + serverPort;
        log.info(str);
        return str;
    }
}
