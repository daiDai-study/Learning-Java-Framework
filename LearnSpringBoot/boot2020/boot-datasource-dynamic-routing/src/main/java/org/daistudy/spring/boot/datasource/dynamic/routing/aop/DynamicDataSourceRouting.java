package org.daistudy.spring.boot.datasource.dynamic.routing.aop;

import lombok.extern.slf4j.Slf4j;
import org.daistudy.spring.boot.datasource.dynamic.routing.util.DataSourceUtil;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author 60055797
 */
@Slf4j
public class DynamicDataSourceRouting extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        //获取当前线程的数据源，如果不存在，那么使用默认数据源
        String datasource = DynamicDataSourceContextHolder.getDataSource();
        if (StringUtils.isEmpty(datasource) || !DataSourceUtil.containsDataSource(datasource)) {
            datasource = DataSourceUtil.DEFAULT_DATA_SOURCE;
        }
        if (!Objects.equals(datasource, DataSourceUtil.DEFAULT_DATA_SOURCE)) {
            log.info("切换的数据源为：{}", datasource);
        }
        return datasource;
    }
}
