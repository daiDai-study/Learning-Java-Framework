package org.daistudy.springbootshiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ResultMap resultMap;

    @GetMapping("/getMessage")
    public ResultMap getMessage(){
        return resultMap.success().message("您拥有管理员的权限，可以获得该接口的信息");
    }
}
