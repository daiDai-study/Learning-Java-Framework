package org.daistudy.springframework.ioc.xml.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectBeanWithProperties {
    public static void main(String[] args) {
        final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        final Object book3 = applicationContext.getBean("book3");
        final Object book4 = applicationContext.getBean("book4");
        final Object book5 = applicationContext.getBean("book5");
        final Object book6 = applicationContext.getBean("book6");
        final Object book7 = applicationContext.getBean("book7");
        System.out.println(book3);
        System.out.println(book4);
        System.out.println(book5);
        System.out.println(book6);
        System.out.println(book7);
    }
}
