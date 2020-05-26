package org.daistudy.springframework.ioc.java.main;

import org.daistudy.springframework.ioc.java.config.JavaConfig;
import org.daistudy.springframework.ioc.java.model.SayHello;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FirstStart {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        final SayHello bean = applicationContext.getBean("hello,hello1", SayHello.class);
        System.out.println(bean.sayHello("spring ioc"));
    }
}
