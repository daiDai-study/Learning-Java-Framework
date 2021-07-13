package org.daistudy.jdbctemplate.datasource.druid.service.impl;

import org.daistudy.jdbctemplate.datasource.druid.entity.User;
import org.daistudy.jdbctemplate.datasource.druid.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> list() {
        return jdbcTemplate.query("select * from user;",
            (rs, num) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("favorites")));
    }

    @Override
    public User findById(int id) {
        return jdbcTemplate.queryForObject("select * from user where id=?;", User.class, id);
    }

    @Override
    public Boolean add(User user) {
        return jdbcTemplate.update("insert into user(name, address, favorites) values(?,?,?);", user.getName(), user.getAddress(), user.getFavorites()) == 1;
    }

    @Override
    public Boolean edit(User user) {
        return jdbcTemplate.update("update user set name=?, address=?, favorites=? where id=?;", user.getName(), user.getAddress(), user.getFavorites(), user.getId()) == 1;
    }

    @Override
    public Boolean delete(User user) {
        return jdbcTemplate.update("delete from user where id=?;", user.getId()) == 1;
    }
}
