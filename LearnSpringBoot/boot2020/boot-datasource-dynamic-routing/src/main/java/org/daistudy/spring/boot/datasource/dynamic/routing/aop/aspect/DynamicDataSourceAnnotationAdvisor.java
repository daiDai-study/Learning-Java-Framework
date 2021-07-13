package org.daistudy.spring.boot.datasource.dynamic.routing.aop.aspect;

import lombok.NonNull;
import org.aopalliance.aop.Advice;
import org.daistudy.spring.boot.datasource.dynamic.routing.annotation.TargetDataSource;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author 60055797
 */
public class DynamicDataSourceAnnotationAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

    private Advice advice;

    private Pointcut pointcut;

    public DynamicDataSourceAnnotationAdvisor(
        @NonNull DynamicDataSourceAnnotationInterceptor dynamicDataSourceAnnotationInterceptor) {
        this.advice = dynamicDataSourceAnnotationInterceptor;
        this.pointcut = buildPointcut();
    }

    /**
     * 定义切点：匹配类型注解或方法注解
     */
    private Pointcut buildPointcut() {
        Pointcut cpc = new AnnotationMatchingPointcut(TargetDataSource.class, true);
        Pointcut mpc = AnnotationMatchingPointcut.forMethodAnnotation(TargetDataSource.class);
        return new ComposablePointcut(cpc).union(mpc);
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }
}
