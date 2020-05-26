package org.daistudy.springframework.ioc.xml.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Autowired
    UserDao userDao2;

    public String hello(){
        return userDao2.hello();
    }
}
