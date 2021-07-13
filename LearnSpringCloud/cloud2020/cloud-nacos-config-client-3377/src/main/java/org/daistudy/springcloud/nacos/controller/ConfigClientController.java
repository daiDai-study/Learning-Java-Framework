package org.daistudy.springcloud.nacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String info;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("info")
    public String info(){
        return serverPort + ": "+ info;
    }
}
