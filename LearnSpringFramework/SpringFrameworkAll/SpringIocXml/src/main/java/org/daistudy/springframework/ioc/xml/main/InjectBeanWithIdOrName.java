package org.daistudy.springframework.ioc.xml.main;

import org.daistudy.springframework.ioc.xml.model.Book;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectBeanWithIdOrName {
    public static void main(String[] args) {
        final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        final Book book1_10 = applicationContext.getBean("book1,book10", Book.class);
        final Book book11 = applicationContext.getBean("book11", Book.class);
        final Book book12 = applicationContext.getBean("book12", Book.class);
        final Book book1 = applicationContext.getBean("book1", Book.class);
        final Book book10 = applicationContext.getBean("book10", Book.class);
        final Book book11_12 = applicationContext.getBean("book11,book12", Book.class);
    }
}
