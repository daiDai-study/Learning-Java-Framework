package org.daistudy.springbootshiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guest")
public class GuestController {
    @Autowired
    private ResultMap resultMap;

    @GetMapping("/enter")
    public ResultMap login(){
        return resultMap.success().message("欢迎进入，您的身份是游客");
    }

    @GetMapping("/getMessage")
    public ResultMap submitLogin(){
        return resultMap.success().message("您拥有获得该接口的信息的权限");
    }
}
