package org.daistudy.spring.boot.datasource.dynamic.routing.aop.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.daistudy.spring.boot.datasource.dynamic.routing.annotation.TargetDataSource;
import org.daistudy.spring.boot.datasource.dynamic.routing.aop.DynamicDataSourceContextHolder;
import org.daistudy.spring.boot.datasource.dynamic.routing.util.DataSourceUtil;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author 60055797
 */
public class DynamicDataSourceAnnotationInterceptor implements MethodInterceptor {

    private static final String DYNAMIC_PREFIX = "#";

    /**
     * 参数发现器
     */
    private static final ParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    /**
     * Express语法解析器
     */
    private static final ExpressionParser PARSER = new SpelExpressionParser();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String dataSourceKey = determineDatasource(invocation);
        if(StringUtils.isEmpty(dataSourceKey)){
            dataSourceKey = DataSourceUtil.DEFAULT_DATA_SOURCE;
        }
        DynamicDataSourceContextHolder.setDataSource(dataSourceKey);
        Object rs = null;
        try {
            rs = invocation.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            DynamicDataSourceContextHolder.setDataSource(DataSourceUtil.DEFAULT_DATA_SOURCE);
        }
        return rs;
    }

    private String determineDatasource(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Object[] arguments = invocation.getArguments();
        TargetDataSource ds = method.isAnnotationPresent(TargetDataSource.class)
                ? method.getAnnotation(TargetDataSource.class)
                : AnnotationUtils.findAnnotation(method.getDeclaringClass(), TargetDataSource.class);
        String key = null;
        if (ds != null) {
            key = ds.value();
        }
        return parseKey(key, method, arguments);
    }

    public static String parseKey(String key, Method method, Object[] arguments){
        if(key != null && !key.isEmpty() && key.startsWith(DYNAMIC_PREFIX)){
            EvaluationContext context = new MethodBasedEvaluationContext(null, method, arguments,
                NAME_DISCOVERER);
            Object value = PARSER.parseExpression(key).getValue(context);
            if(value != null){
                key = value.toString();
            }
        }
        return key;
    }
}
