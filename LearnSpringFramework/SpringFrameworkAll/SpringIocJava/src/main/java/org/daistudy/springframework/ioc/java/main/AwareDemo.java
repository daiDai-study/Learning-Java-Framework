package org.daistudy.springframework.ioc.java.main;

import org.daistudy.springframework.ioc.java.bean.UserAware;
import org.daistudy.springframework.ioc.java.config.JavaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AwareDemo {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
//        final UserAware userAware = applicationContext.getBean("userAware", UserAware.class);
//        System.out.println(userAware.sayHello());
    }
}
