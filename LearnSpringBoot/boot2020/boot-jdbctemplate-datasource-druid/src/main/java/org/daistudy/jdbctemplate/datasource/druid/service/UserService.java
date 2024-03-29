package org.daistudy.jdbctemplate.datasource.druid.service;

import org.daistudy.jdbctemplate.datasource.druid.entity.User;

import java.util.List;

public interface UserService {

    List<User> list();

    User findById(int id);

    Boolean add(User user);

    Boolean edit(User user);

    Boolean delete(User user);
}
