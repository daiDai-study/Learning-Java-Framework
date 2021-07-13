package org.daistudy.spring.boot.org.jdbctemplate.service;

import org.daistudy.spring.boot.org.jdbctemplate.entity.User;

import java.util.List;

public interface UserService {

    List<User> list();

    User findById(int id);

    Boolean add(User user);

    Boolean edit(User user);

    Boolean delete(User user);
}
