package org.daistudy.springframework.jdbc.main;

import org.daistudy.springframework.jdbc.config.JdbcConfig;
import org.daistudy.springframework.jdbc.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SimpleJdbcOperations {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JdbcConfig.class);
        final JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        final RowMapper<User> userRowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                final User user1 = new User();
                user1.setId(resultSet.getInt("id"));
                user1.setName(resultSet.getString("username"));
                user1.setAddress(resultSet.getString("address"));
                user1.setFavorites(resultSet.getString("favorites"));
                return user1;
            }
        };

        final User user = jdbcTemplate.queryForObject("select * from user where id = ? ;", userRowMapper, 1);
        System.out.println(user);

        final List<User> query = jdbcTemplate.query("select * from user", userRowMapper);
        System.out.println(query);
    }
}
