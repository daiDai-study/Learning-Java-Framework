package org.daistudy.springframework.aop.simpledemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect // 表示这是一个切面
public class ActionAspect {
    // 统一定义切点
    @Pointcut("@annotation(Action)")
    public void pointcut(){}

    /**
     * 可以统一定义切点
     * 第一个 * 表示要拦截的目标方法返回值任意（也可以明确指定返回值类型
     * 第二个 * 表示包中的任意类（也可以明确指定类
     * 第三个 * 表示类中的任意方法
     * 最后面的两个点表示方法参数任意，个数任意，类型任意
     */
    @Pointcut("execution(* org.daistudy.springframework.aop.simpledemo.*.*(..))")
    public void pointcut2(){}

    // 前置通知
    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint){
        final Signature signature = joinPoint.getSignature();
        final String name = signature.getName();
        System.out.println(name + "方法开始执行...");
    }
    // 后置通知
    @After(value = "pointcut()")
    public void after(JoinPoint joinPoint){
        final Signature signature = joinPoint.getSignature();
        final String name = signature.getName();
        System.out.println(name + "方法执行结束...");
    }
    // 返回通知
    @AfterReturning(value = "pointcut()", returning = "r")
    public void returing(JoinPoint joinPoint, Integer r){
        final Signature signature = joinPoint.getSignature();
        final String name = signature.getName();
        System.out.println(name + "方法返回：" + r);
    }
    // 异常通知
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e){
        final Signature signature = joinPoint.getSignature();
        final String name = signature.getName();
        System.out.println(name + "方法抛异常了：" + e.getMessage());
    }
    // 环绕通知
    @Around(value = "pointcut2()")
    public Object around(ProceedingJoinPoint joinPoint){
        final Signature signature = joinPoint.getSignature();
        final String name = signature.getName();

        Object proceed = null;
        try {
            System.out.println(name + "方法开始执行...around");
            proceed = joinPoint.proceed();
            System.out.println(name + "方法执行结束...around");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println(name + "方法抛异常了：" + throwable.getMessage() + "around");
        }
        System.out.println(name + "方法返回：" + proceed + "around");
        return proceed;
    }
}
