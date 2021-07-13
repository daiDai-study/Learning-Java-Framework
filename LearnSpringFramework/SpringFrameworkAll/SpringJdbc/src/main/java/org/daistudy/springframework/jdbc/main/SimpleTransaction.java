package org.daistudy.springframework.jdbc.main;

import org.daistudy.springframework.jdbc.config.JdbcConfig;
import org.daistudy.springframework.jdbc.dao.UserDao;
import org.daistudy.springframework.jdbc.entity.User;
import org.daistudy.springframework.jdbc.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 为使事务生效：
 * 1.在配置类上开启事务管理：@EnableTransactionManager
 * 2.在配置类中配置 Bean ：默认可以直接返回一个 DataSourceTransactionManager
 * 3.在需要使用事务的类和方法上配置：
 *      3.1、类必须注册到 Spring IoC 容器中；
 *      3.2、在方法上使用注解 @Transactional
 */
public class SimpleTransaction {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JdbcConfig.class);
        final UserService userService = applicationContext.getBean("userService", UserService.class);
        User user = new User();
        user.setName("张三456");
        user.setAddress("江苏南京");
        user.setFavorites("足球；篮球");
        final int insert = userService.insert(user);
        System.out.println(insert);
    }
}
