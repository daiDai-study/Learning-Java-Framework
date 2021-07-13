package org.daistudy.spring.boot.datasource.dynamic.routing.service.impl;

import com.zaxxer.hikari.HikariDataSource;
import org.daistudy.spring.boot.datasource.dynamic.routing.entity.DataSourceConfig;
import org.daistudy.spring.boot.datasource.dynamic.routing.mapper.DataSourceConfigMapper;
import org.daistudy.spring.boot.datasource.dynamic.routing.service.DataSourceConfigService;
import org.daistudy.spring.boot.datasource.dynamic.routing.util.DataSourceUtil;
import org.daistudy.spring.boot.datasource.dynamic.routing.util.SpringContextUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataSourceConfigServiceImpl implements DataSourceConfigService {

    @Resource
    private DataSourceConfigMapper mapper;

    @Override
    public void loadDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        DataSource defaultDataSource = SpringContextUtil.getBean("defaultDataSource", DataSource.class);
        dataSourceMap.put(DataSourceUtil.DEFAULT_DATA_SOURCE, defaultDataSource);

        List<DataSourceConfig> list = this.list();
        for (DataSourceConfig config : list) {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setUsername(config.getUsername());
            dataSource.setPassword(config.getPassword());
            dataSource.setJdbcUrl(config.getJdbcUrl());
            dataSource.setDriverClassName(config.getMysqlDriver());
//            DruidDataSource dataSource = new DruidDataSource();
//            dataSource.setUrl(config.getJdbcUrl());
//            dataSource.setUsername(config.getUsername());
//            dataSource.setPassword(config.getPassword());
//            dataSource.setDriverClassName(config.getMysqlDriver());
            dataSourceMap.put(config.getDatabase(), dataSource);
        }
        DataSourceUtil.addDataSources(dataSourceMap);
    }

    @Override
    public List<DataSourceConfig> list() {
        return mapper.list();
    }
}
