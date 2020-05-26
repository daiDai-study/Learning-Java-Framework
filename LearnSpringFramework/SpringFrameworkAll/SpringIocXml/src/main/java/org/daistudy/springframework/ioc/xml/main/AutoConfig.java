package org.daistudy.springframework.ioc.xml.main;

import org.daistudy.springframework.ioc.xml.bean.UserController;
import org.daistudy.springframework.ioc.xml.bean.UserDao;
import org.daistudy.springframework.ioc.xml.bean.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoConfig {
    public static void main(String[] args) {
        final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        final UserService userService = applicationContext.getBean("userService", UserService.class);
        final UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
        final UserDao userDao2 = applicationContext.getBean("userDao2", UserDao.class);
        final UserDao userDao3 = applicationContext.getBean("userDao3", UserDao.class);
        System.out.println(userService.hello());
        // final UserController userController = applicationContext.getBean("userController", UserController.class);
    }
}
