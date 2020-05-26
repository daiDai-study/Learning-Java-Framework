package org.daistudy.springframework.ioc.java.main;

import org.daistudy.springframework.ioc.java.config.JavaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * JavaConfig 配置类本身也被 Spring 容器看为一个 Bean
 */
public class BeanOfJavaConfig {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        final JavaConfig javaConfig = applicationContext.getBean("javaConfig", JavaConfig.class);
        System.out.println(javaConfig);
    }
}
