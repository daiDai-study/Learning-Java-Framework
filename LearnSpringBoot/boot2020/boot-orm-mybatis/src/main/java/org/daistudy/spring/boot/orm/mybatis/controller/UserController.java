package org.daistudy.spring.boot.orm.mybatis.controller;

import org.daistudy.spring.boot.orm.mybatis.entity.ApiResult;
import org.daistudy.spring.boot.orm.mybatis.entity.User;
import org.daistudy.spring.boot.orm.mybatis.service.UserService;
import org.daistudy.spring.boot.orm.mybatis.util.SpringContextUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private DataSource dataSource;

    @GetMapping("/dataSource")
    public ApiResult dataSource(){
        DataSource datasourceWithGet = SpringContextUtil.getBean("dataSource", DataSource.class);
        DataSource datasourceWithInject = dataSource;
        Map<String, DataSource> map = new HashMap<>();
        map.put("get", datasourceWithGet);
        map.put("inject", datasourceWithInject);
        return ApiResult.ofSuccess(map);
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
        if (userService.update(user)) {
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
