package org.daistudy.springcloud.provider;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class EurekaHystrixProviderService8001 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaHystrixProviderService8001.class, args);
    }

    /**
     * 此配置是为了服务监控
     * 因为 springboot 的默认路径不是 "/hystrix.stream"
     * 所以需要在项目中配置对应的 servlet
     */
    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet(){
        HystrixMetricsStreamServlet hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> bean = new ServletRegistrationBean<>(hystrixMetricsStreamServlet);
        bean.setLoadOnStartup(1);
        bean.addUrlMappings("/hystrix.stream");
        bean.setName("HystrixMetricsStreamServlet");
        return bean;
    }
}
