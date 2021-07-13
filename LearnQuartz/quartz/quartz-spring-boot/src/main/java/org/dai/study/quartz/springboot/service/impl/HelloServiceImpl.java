package org.dai.study.quartz.springboot.service.impl;

import org.dai.study.quartz.springboot.service.HelloService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String str) {
        if (StringUtils.isEmpty(str)) {
            return "hello world";
        }
        return "hello " + str;
    }
}
