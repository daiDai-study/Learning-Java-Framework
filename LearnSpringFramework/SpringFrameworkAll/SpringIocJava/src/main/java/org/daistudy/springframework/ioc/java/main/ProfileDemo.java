package org.daistudy.springframework.ioc.java.main;

import org.daistudy.springframework.ioc.java.config.JavaConfig;
import org.daistudy.springframework.ioc.java.model.DataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProfileDemo {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getEnvironment().setActiveProfiles("dev");
        applicationContext.register(JavaConfig.class);
        applicationContext.refresh();
        final DataSource ds = applicationContext.getBean("ds", DataSource.class);
        System.out.println(ds);
    }
}
