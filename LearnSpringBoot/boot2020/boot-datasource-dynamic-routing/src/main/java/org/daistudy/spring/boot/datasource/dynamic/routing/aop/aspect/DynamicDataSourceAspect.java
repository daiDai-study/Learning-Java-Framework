package org.daistudy.spring.boot.datasource.dynamic.routing.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.daistudy.spring.boot.datasource.dynamic.routing.annotation.TargetDataSource;
import org.daistudy.spring.boot.datasource.dynamic.routing.aop.DynamicDataSourceContextHolder;
import org.daistudy.spring.boot.datasource.dynamic.routing.util.DataSourceUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(org.daistudy.spring.boot.datasource.dynamic.routing.annotation.TargetDataSource)")
    private void pointCut(){

    }

    /**
     * 执行前切换成指定数据源
     */
    @Before("@annotation(targetDataSource)")
    public void before(JoinPoint joinPoint, TargetDataSource targetDataSource){
        String key = targetDataSource.value();
        Object[] args = joinPoint.getArgs();
        Method method = null;
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = null;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) signature;
        Object target = joinPoint.getTarget();
        try {
            method = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        key = DynamicDataSourceAnnotationInterceptor.parseKey(key, method, args);
        if(StringUtils.isEmpty(key)){
            key = DataSourceUtil.DEFAULT_DATA_SOURCE;
        }
        DynamicDataSourceContextHolder.setDataSource(key);
    }

    /**
     * 执行后切换回默认数据源
     */
    @After("@annotation(targetDataSource)")
    public void after(JoinPoint joinPoint, TargetDataSource targetDataSource){
        DynamicDataSourceContextHolder.setDataSource(DataSourceUtil.DEFAULT_DATA_SOURCE);
    }
}
