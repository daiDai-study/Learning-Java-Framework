package org.daistudy.springframework.ioc.java.config;

import okhttp3.OkHttpClient;
import org.daistudy.springframework.ioc.java.bean.UserDao;
import org.daistudy.springframework.ioc.java.condition.LinuxCondition;
import org.daistudy.springframework.ioc.java.condition.WindowsCondition;
import org.daistudy.springframework.ioc.java.model.*;
import org.daistudy.springframework.ioc.java.utils.FactoryDemo;
import org.daistudy.springframework.ioc.java.utils.OkHttpUtils;
import org.daistudy.springframework.ioc.java.utils.OkhttpUtilsWithObject;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * 通过 @Configuration 注解将该类声明一个 Java Bean 的配置类，作用相当于 XML 配置中的 applicationContext.xml 文件
 */
@Configuration
@ComponentScan(basePackages = "org.daistudy.springframework.ioc.java", useDefaultFilters = true, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
})
public class JavaConfig {

    /**
     * 通过 @Bean 注解将该方法的返回值作为一个 Java Bean，作用相当于 XML 配置中的 applicationContext.xml 文件中的 bean 节点（标签）
     * Bean 的 id 默认为方法名
     * @return
     */
    @Bean("hello,hello1")
    SayHello sayHello(){
        return new SayHello();
    }

    @Bean("showCmd")
    @Conditional(WindowsCondition.class)
    ShowCmd winCmd(){
        return new WindowsShowCmd();
    }

    @Bean("showCmd")
    @Conditional(LinuxCondition.class)
    ShowCmd linuxCmd(){
        return new LinuxShowCmd();
    }

    @Bean("ds")
    @Profile("dev")
    DataSource devDataSource(){
        final DataSource dataSource = new DataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/dev");
        dataSource.setUsername("dev");
        dataSource.setPassword("dev");
        return dataSource;
    }

    @Bean("ds")
    @Profile("prod")
    DataSource prodDataSource(){
        final DataSource dataSource = new DataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/prod");
        dataSource.setUsername("prod");
        dataSource.setPassword("prod");
        return dataSource;
    }

    @Bean("staticOkHttpClient")
    OkHttpClient staticOkHttpClient(){
        return OkHttpUtils.getInstance();
    }

    @Bean("nonStaticOkHttpClient")
    OkHttpClient nonStaticOkHttpClient(OkhttpUtilsWithObject okhttpUtilsWithObject){
        return okhttpUtilsWithObject.getInstance();
    }

    @Bean
    OkhttpUtilsWithObject okhttpUtilsWithObject(){
        return new OkhttpUtilsWithObject();
    }

    @Bean
    FactoryDemo factoryDemo(){
        return new FactoryDemo();
    }

    @Bean
    FactoryDemoBean factoryDemoBean(FactoryDemo factoryDemo) {
        return factoryDemo.getObject();
    }

//    @Bean("userDao2")
//    UserDao userDao(){
//        return new UserDao();
//    }
}
