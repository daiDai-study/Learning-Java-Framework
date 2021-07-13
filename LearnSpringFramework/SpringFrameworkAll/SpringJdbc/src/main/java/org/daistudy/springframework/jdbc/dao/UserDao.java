package org.daistudy.springframework.jdbc.dao;

import org.daistudy.springframework.jdbc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(User user){
        return jdbcTemplate.update("insert user (name, address, favorites) values (?, ?, ?)", user.getName(), user.getAddress(), user.getFavorites());
    }

    public int updateUserName(String username , int userId){
        return jdbcTemplate.update("update user set username = ? where id = ?;", username, userId);
    }

    public int updateAddress(String address , int userId){
        return jdbcTemplate.update("update user set address = ? where id = ?;", address, userId);
    }
}
