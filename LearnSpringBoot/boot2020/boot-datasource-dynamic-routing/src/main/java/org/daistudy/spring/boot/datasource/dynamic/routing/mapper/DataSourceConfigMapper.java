package org.daistudy.spring.boot.datasource.dynamic.routing.mapper;

import org.daistudy.spring.boot.datasource.dynamic.routing.entity.DataSourceConfig;

import java.util.List;

public interface DataSourceConfigMapper {
    List<DataSourceConfig> list();
}
