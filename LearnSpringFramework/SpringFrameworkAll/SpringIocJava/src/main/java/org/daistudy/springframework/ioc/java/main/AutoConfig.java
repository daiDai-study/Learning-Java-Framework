package org.daistudy.springframework.ioc.java.main;

import org.daistudy.springframework.ioc.java.bean.UserController;
import org.daistudy.springframework.ioc.java.bean.UserService;
import org.daistudy.springframework.ioc.java.config.JavaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoConfig {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        final UserService userService = applicationContext.getBean("userService", UserService.class);
        final UserController userController = applicationContext.getBean("userController", UserController.class);
    }
}
