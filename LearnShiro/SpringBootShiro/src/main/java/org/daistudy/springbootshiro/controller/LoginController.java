package org.daistudy.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.daistudy.springbootshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class LoginController {
    @Autowired
    private ResultMap resultMap;

    @Autowired
    private UserService userService;

    @GetMapping("/notLogin")
    public ResultMap notLogin(){
        return resultMap.success().message("您尚未登录");
    }

    @GetMapping("/notAuth")
    public ResultMap notAuth(){
        return resultMap.success().message("您没有权限");
    }

    @GetMapping("/logout")
    public ResultMap logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return resultMap.success().message("注销成功");
    }

    @PostMapping("/login")
    public ResultMap login(String username, String password){
        String passwordByUsername = userService.getPasswordByUsername(username);
        if(password == null || !password.equals(passwordByUsername)){
            return resultMap.fail().message("登录失败，用户名或密码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken authenticationToken = new UsernamePasswordToken(username, password);
        subject.login(authenticationToken);
        Set<String> rolesByUsername = userService.getRolesByUsername(username);
        if(rolesByUsername.contains("admin")){
            return resultMap.success().message("欢迎来到管理员界面").data(authenticationToken);
        }
        if(rolesByUsername.contains("user")){
            return resultMap.success().message("欢迎登录").data(authenticationToken);
        }
        return resultMap.fail().message("权限错误");
    }
}
