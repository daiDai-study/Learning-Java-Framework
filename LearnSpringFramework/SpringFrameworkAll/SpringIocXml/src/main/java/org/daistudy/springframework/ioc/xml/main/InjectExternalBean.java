package org.daistudy.springframework.ioc.xml.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectExternalBean {
    public static void main(String[] args) {
        final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        final Object okHttpClient = applicationContext.getBean("okHttpClient");
        final Object okHttpClient2 = applicationContext.getBean("okHttpClient2");
        System.out.println(okHttpClient);
        System.out.println(okHttpClient2);
    }
}
