package org.daistudy.springframework.aop.simpledemo;

import org.springframework.stereotype.Component;

@Component("myCalculator")
public class MyCalculatorImpl {
    @Action
    public Integer add(int a, int b){
        return a + b;
    }

    public Integer minus(int a, int b){
        return a - b;
    }

    @Action
    public Integer divide(int a, int b){
        return a/b;
    }
}
