package org.dai.study.quartz.springboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.dai.study.quartz.springboot.mapper")
public class MybatisConfig {


}
