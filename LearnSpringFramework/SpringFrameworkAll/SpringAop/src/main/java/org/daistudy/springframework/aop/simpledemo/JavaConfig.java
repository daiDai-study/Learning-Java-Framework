package org.daistudy.springframework.aop.simpledemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "org.daistudy.springframework.aop.simpledemo")
@EnableAspectJAutoProxy // 开启自动代理
public class JavaConfig {
}
