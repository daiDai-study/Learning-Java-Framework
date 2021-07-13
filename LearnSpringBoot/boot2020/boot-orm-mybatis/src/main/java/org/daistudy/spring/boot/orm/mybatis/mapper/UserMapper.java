package org.daistudy.spring.boot.orm.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.daistudy.spring.boot.orm.mybatis.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> list();

    User findById(int id);

    Integer insert(User user);

    Integer update(User user);

    Integer delete(User user);
}
