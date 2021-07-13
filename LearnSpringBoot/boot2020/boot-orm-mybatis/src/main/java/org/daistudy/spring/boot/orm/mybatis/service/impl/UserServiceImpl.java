package org.daistudy.spring.boot.orm.mybatis.service.impl;

import org.daistudy.spring.boot.orm.mybatis.entity.User;
import org.daistudy.spring.boot.orm.mybatis.mapper.UserMapper;
import org.daistudy.spring.boot.orm.mybatis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public Boolean add(User user) {
        return userMapper.insert(user) == 1;
    }

    @Override
    public Boolean update(User user) {
        return userMapper.update(user) == 1;
    }

    @Override
    public Boolean delete(User user) {
        return userMapper.delete(user) == 1;
    }
}
