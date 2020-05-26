package org.daistudy.springframework.ioc.java.bean;

import org.springframework.stereotype.Repository;

@Repository("userDao1")
public class UserDao {
    public String hello() {
        return "hello userDao";
    }
}
