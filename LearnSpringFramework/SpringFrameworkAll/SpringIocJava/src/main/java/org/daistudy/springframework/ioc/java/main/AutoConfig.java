package org.daistudy.springframework.ioc.java.main;

import org.daistudy.springframework.ioc.java.bean.UserController;
import org.daistudy.springframework.ioc.java.bean.UserService;
import org.daistudy.springframework.ioc.java.config.JavaConfig;
import org.daistudy.springframework.ioc.java.test.bean.samename.model.ModelA;
import org.daistudy.springframework.ioc.java.test.bean.samename.model.ModelB;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoConfig {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        final UserService userService = applicationContext.getBean("userService", UserService.class);
//        final UserController userController = applicationContext.getBean("userController", UserController.class);

        Object model = applicationContext.getBean("model");
        if(model instanceof ModelA){
            System.out.println("ModelA生效");
        }else if(model instanceof ModelB){
            System.out.println("ModelB生效");
        }
    }
}
