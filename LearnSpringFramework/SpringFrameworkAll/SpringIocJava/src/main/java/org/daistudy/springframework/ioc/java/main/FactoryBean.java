package org.daistudy.springframework.ioc.java.main;

import okhttp3.OkHttpClient;
import org.daistudy.springframework.ioc.java.config.JavaConfig;
import org.daistudy.springframework.ioc.java.model.FactoryDemoBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FactoryBean {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        final OkHttpClient staticOkHttpClient = context.getBean("staticOkHttpClient", OkHttpClient.class);
        final OkHttpClient nonStaticOkHttpClient = context.getBean("nonStaticOkHttpClient", OkHttpClient.class);
        System.out.println(staticOkHttpClient);
        System.out.println(nonStaticOkHttpClient);

        final FactoryDemoBean factoryDemoBean = context.getBean("factoryDemoBean", FactoryDemoBean.class);
        System.out.println(factoryDemoBean);
    }
}
