package org.daistudy.springframework.ioc.xml.main;

import org.daistudy.springframework.ioc.xml.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectComplexProperties {
    public static void main(String[] args) {
        final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        final User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
    }
}
