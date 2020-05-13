package org.daistudy.springcloudclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping()
public class ClientController {

    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/hello")
    @ResponseBody
    public String Hello(){
        return "我是一个微服务！" + serverPort;
    }
}
