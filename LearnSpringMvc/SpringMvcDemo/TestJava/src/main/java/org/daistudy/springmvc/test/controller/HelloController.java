package org.daistudy.springmvc.test.controller;

import org.daistudy.springmvc.test.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @RequestMapping({"/hello", "/"})
    public String hello(){
        return helloService.hello("world");
    }
}
