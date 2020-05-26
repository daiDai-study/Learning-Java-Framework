package org.daistudy.springframework.ioc.java.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
//    @Autowired
    UserDao userDao;

    @Resource
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

//    public UserService(UserDao userDao){
//        this.userDao = userDao;
//    }

    public String sayHello(){
        return userDao.hello();
    }
}
