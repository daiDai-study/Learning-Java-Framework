package org.daistudy.spring.boot.datasource.dynamic.routing.config;

import lombok.extern.slf4j.Slf4j;
import org.daistudy.spring.boot.datasource.dynamic.routing.aop.DynamicDataSourceRouting;
import org.daistudy.spring.boot.datasource.dynamic.routing.aop.aspect.DynamicDataSourceAnnotationAdvisor;
import org.daistudy.spring.boot.datasource.dynamic.routing.aop.aspect.DynamicDataSourceAnnotationInterceptor;
import org.daistudy.spring.boot.datasource.dynamic.routing.util.DataSourceUtil;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan({"org.daistudy.spring.boot.datasource.dynamic.routing.mapper"})
@Slf4j
public class DynamicDataSourceConfig {

    /**
     * 通过方法拦截和切点定义实现切面操作
     */
//    @Bean
//    public DynamicDataSourceAnnotationAdvisor dynamicDataSourceAnnotationAdvisor() {
//        DynamicDataSourceAnnotationInterceptor interceptor = new DynamicDataSourceAnnotationInterceptor();
//        DynamicDataSourceAnnotationAdvisor advisor = new DynamicDataSourceAnnotationAdvisor(interceptor);
//        advisor.setOrder(-1000);
//        return advisor;
//    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dynamicDataSource());
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:**/xml/*Mapper.xml"));
        return factoryBean;
    }

    @Bean
    public DynamicDataSourceRouting dynamicDataSource(){
        DynamicDataSourceRouting dynamic = new DynamicDataSourceRouting();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceUtil.DEFAULT_DATA_SOURCE, defaultDataSource());
//        dynamic.setDefaultTargetDataSource(DataSourceUtil.DEFAULT_DATA_SOURCE);
//        dynamic.setDefaultTargetDataSource(defaultDataSource());
        dynamic.setTargetDataSources(dataSourceMap);
//        dynamic.afterPropertiesSet();
        return dynamic;
    }

//    @Bean(name = "defaultDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.druid")
//    public DataSource defaultDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }

    @Primary
    @Bean(name = "defaultDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource defaultDataSource() {
        return DataSourceBuilder.create().build();
    }
}
