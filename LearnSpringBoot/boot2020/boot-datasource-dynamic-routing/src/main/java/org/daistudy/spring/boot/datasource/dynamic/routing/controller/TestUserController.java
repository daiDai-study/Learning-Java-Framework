package org.daistudy.spring.boot.datasource.dynamic.routing.controller;

import org.daistudy.spring.boot.datasource.dynamic.routing.entity.ApiResult;
import org.daistudy.spring.boot.datasource.dynamic.routing.entity.TestUser;
import org.daistudy.spring.boot.datasource.dynamic.routing.service.TestUserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class TestUserController {

    @Resource
    private TestUserService testUserService;

    @GetMapping("/list")
    public ApiResult listByDb(@RequestParam(value = "db", required = false) String db){
        List<TestUser> list;
        if(StringUtils.isEmpty(db)){
            list = testUserService.list();
        }else{
            list = testUserService.list(db);
        }
        return ApiResult.ofSuccess(list);
    }
}
