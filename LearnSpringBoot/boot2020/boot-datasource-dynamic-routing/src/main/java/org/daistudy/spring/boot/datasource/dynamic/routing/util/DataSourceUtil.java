package org.daistudy.spring.boot.datasource.dynamic.routing.util;

import lombok.extern.slf4j.Slf4j;
import org.daistudy.spring.boot.datasource.dynamic.routing.aop.DynamicDataSourceRouting;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 60055797
 */
@Slf4j
public class DataSourceUtil {

    private static final Map<Object, Object> DATA_SOURCE_MAP = new ConcurrentHashMap<>();

    public static final String DEFAULT_DATA_SOURCE = "default";

    private static void flushDataSource() {
        // 获取spring管理的dynamicDataSource
        DynamicDataSourceRouting dynamicDataSourceRouting = (DynamicDataSourceRouting) SpringContextUtil.getBean("dynamicDataSource");
        // 将数据源设置到 targetDataSources
        dynamicDataSourceRouting.setTargetDataSources(DATA_SOURCE_MAP);
        // 将 targetDataSources 中的连接信息放入 resolvedDataSources 管理
        dynamicDataSourceRouting.afterPropertiesSet();
    }

    public static void addDataSource(String key, DataSource dataSource) {
        log.info("新增一个数据源：{}；{}", key, dataSource);
        DATA_SOURCE_MAP.put(key, dataSource);
        flushDataSource();
    }

    public static void removeDataSource(String key) {
        log.info("移除一个数据源：{}", key);
        DATA_SOURCE_MAP.remove(key);
        flushDataSource();
    }

    public static void addDataSources(Map<Object, Object> dataSourceMap) {
        log.info("新增多个数据源：{}", dataSourceMap);
        DATA_SOURCE_MAP.putAll(dataSourceMap);
        flushDataSource();
    }

    public static void replaceKeyOfDataSource(String oldKey, String newKey) {
        DATA_SOURCE_MAP.put(newKey, DATA_SOURCE_MAP.get(oldKey));
        DATA_SOURCE_MAP.remove(oldKey);
        flushDataSource();
    }

    /**
     * 判断数据源是否存在
     * @param dataSourceKey
     * @return
     */
    public static boolean containsDataSource(String dataSourceKey) {
        return DATA_SOURCE_MAP.containsKey(dataSourceKey);
    }
}
