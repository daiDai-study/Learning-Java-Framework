package org.daistudy.spring.boot.datasource.dynamic.routing.service;

import org.daistudy.spring.boot.datasource.dynamic.routing.entity.DataSourceConfig;

import java.util.List;

public interface DataSourceConfigService {

    List<DataSourceConfig> list();

    void loadDataSource();
}
