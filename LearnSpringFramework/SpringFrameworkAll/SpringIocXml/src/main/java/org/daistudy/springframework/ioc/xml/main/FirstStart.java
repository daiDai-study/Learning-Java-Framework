package org.daistudy.springframework.ioc.xml.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 不同方式去加载配置文件时，会加载多次，并构成多个 Spring IoC 容器
 */
public class FirstStart {
    public static void main(String[] args) {
        // ClassPathXmlApplicationContext 会从 classpath 下查找配置文件
        ClassPathXmlApplicationContext applicationContext1 = new ClassPathXmlApplicationContext("applicationContext.xml");
        // FileSystemXmlApplicationContext 从操作系统路径（绝对路径或相对路径）下去寻找配置文件
        FileSystemXmlApplicationContext applicationContext2 = new FileSystemXmlApplicationContext("SpringIocXml/src/main/resources/applicationContext.xml");
        final Object book1 = applicationContext1.getBean("book");
        final Object book2 = applicationContext2.getBean("book");
        System.out.println(book1);
        System.out.println(book2);
    }
}
