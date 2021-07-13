package org.daistudy.spring.boot.orm.mybatis.service;

import org.daistudy.spring.boot.orm.mybatis.entity.User;

import java.util.List;

public interface UserService {

    List<User> list();

    User findById(int id);

    Boolean add(User user);

    Boolean update(User user);

    Boolean delete(User user);
}
