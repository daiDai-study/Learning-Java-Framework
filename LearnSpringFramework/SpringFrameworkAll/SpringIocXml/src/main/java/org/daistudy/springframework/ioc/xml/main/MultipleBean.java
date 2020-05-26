package org.daistudy.springframework.ioc.xml.main;

import org.daistudy.springframework.ioc.xml.model.Book;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过类型（Class）去获取 Bean 时，需要先确保 Spring IoC 容器中近包含一个该类的 Bean
 */
public class MultipleBean {
    public static void main(String[] args) {
        final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        final Book bean = applicationContext.getBean(Book.class);
    }
}
