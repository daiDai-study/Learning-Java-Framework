package org.daistudy.springbootshiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ResultMap resultMap;

    @GetMapping("/getMessage")
    public ResultMap getMessage(){
        return resultMap.success().message("您拥有用户权限，可以获得该接口的信息");
    }
}
