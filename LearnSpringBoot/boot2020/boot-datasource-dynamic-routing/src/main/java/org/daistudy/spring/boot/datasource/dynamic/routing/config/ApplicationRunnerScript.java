package org.daistudy.spring.boot.datasource.dynamic.routing.config;

import lombok.extern.slf4j.Slf4j;
import org.daistudy.spring.boot.datasource.dynamic.routing.service.DataSourceConfigService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * spring启动工具之后的回调工具类,目前仅用于应用启动之后连接数据库中已存在的数据源，
 * 目标是将添加自定义数据源的功能封装成沙盒，不依赖外界任何代码，方便未来拆除多数据源功能。
 */
@Component
@Slf4j
public class ApplicationRunnerScript implements ApplicationRunner {

    @Resource
    private DataSourceConfigService dataSourceConfigService;

//    public ApplicationRunnerScript(DataSourceConfigService dataSourceConfigService){
//        this.dataSourceConfigService = dataSourceConfigService;
//    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("初始加载动态数据源列表：");
        dataSourceConfigService.loadDataSource();
    }
}
