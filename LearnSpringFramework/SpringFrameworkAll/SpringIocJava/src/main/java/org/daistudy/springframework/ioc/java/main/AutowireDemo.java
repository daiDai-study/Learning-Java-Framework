package org.daistudy.springframework.ioc.java.main;

import org.daistudy.springframework.ioc.java.bean.UserDao;
import org.daistudy.springframework.ioc.java.bean.UserService;
import org.daistudy.springframework.ioc.java.config.JavaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowireDemo {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        final UserService userService = applicationContext.getBean("userService", UserService.class);
        final boolean userDao = applicationContext.containsBean("userDao");
        final boolean userDao1 = applicationContext.containsBean("userDao1");
        final boolean userDao2 = applicationContext.containsBean("userDao2");
        System.out.println("userDao is existed: " + userDao);
        System.out.println("userDao1 is existed: " + userDao1);
        System.out.println("userDao2 is existed: " + userDao2);
        System.out.println(userService.sayHello());
    }
}
