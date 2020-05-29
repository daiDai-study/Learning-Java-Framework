package org.daistudy.springframework.aop.simpledemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        final MyCalculatorImpl myCalculator = applicationContext.getBean("myCalculator", MyCalculatorImpl.class);
//        System.out.println(myCalculator.add(2, 1));
        System.out.println(myCalculator.minus(2, 1));
//        System.out.println(myCalculator.divide(2, 0));
    }
}
