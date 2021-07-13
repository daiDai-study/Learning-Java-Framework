package org.daistudy.jdbctemplate.datasource.druid.controller;

import org.daistudy.jdbctemplate.datasource.druid.entity.ApiResult;
import org.daistudy.jdbctemplate.datasource.druid.entity.User;
import org.daistudy.jdbctemplate.datasource.druid.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private DataSource dataSource;

    @GetMapping("/ds")
    public ApiResult ds(){
        return ApiResult.ofSuccess(dataSource);
    }

    @GetMapping("/list")
    public ApiResult list(){
        List<User> list = userService.list();
        return ApiResult.ofSuccess(list);
    }

    @PostMapping("/add")
    public ApiResult add(@RequestBody User user){
        if (userService.add(user)) {
            return ApiResult.ofSuccess(user);
        }else{
            return ApiResult.ofFail();
        }
    }

    @PutMapping("/edit")
    public ApiResult edit(@RequestBody User user){
        if (userService.edit(user)) {
            return ApiResult.ofSuccess(user);
        }else{
            return ApiResult.ofFail();
        }
    }

    @DeleteMapping("/delete")
    public ApiResult delete(@RequestBody User user){
        if (userService.delete(user)) {
            return ApiResult.ofSuccess(user);
        }else{
            return ApiResult.ofFail();
        }
    }

}
