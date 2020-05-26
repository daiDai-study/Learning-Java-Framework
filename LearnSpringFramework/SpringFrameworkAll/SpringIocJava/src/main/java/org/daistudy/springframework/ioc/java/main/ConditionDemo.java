package org.daistudy.springframework.ioc.java.main;

import org.daistudy.springframework.ioc.java.config.JavaConfig;
import org.daistudy.springframework.ioc.java.model.ShowCmd;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConditionDemo {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        final ShowCmd showCmd = applicationContext.getBean("showCmd", ShowCmd.class);
        System.out.println(showCmd.showCmd());
    }
}
