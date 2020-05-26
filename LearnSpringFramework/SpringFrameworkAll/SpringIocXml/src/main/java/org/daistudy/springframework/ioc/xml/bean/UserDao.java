package org.daistudy.springframework.ioc.xml.bean;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public String hello() {
        return "hello userDao";
    }
}
