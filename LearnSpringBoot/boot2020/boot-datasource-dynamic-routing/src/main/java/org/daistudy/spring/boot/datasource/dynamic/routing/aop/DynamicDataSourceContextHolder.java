package org.daistudy.spring.boot.datasource.dynamic.routing.aop;

import lombok.extern.slf4j.Slf4j;
import org.daistudy.spring.boot.datasource.dynamic.routing.util.DataSourceUtil;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 动态数据源上下文
 * @author 60055797
 */
@Slf4j
public class DynamicDataSourceContextHolder {

    private DynamicDataSourceContextHolder() {
    }

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dataSourceKey
     */
    public static void setDataSource(@NotNull String dataSourceKey) {
        // 设置的数据源于现有数据源相同，则无须重新设置
        if(Objects.equals(CONTEXT_HOLDER.get(), dataSourceKey)){
            return;
        }
        if (DataSourceUtil.containsDataSource(dataSourceKey)) {
            CONTEXT_HOLDER.set(dataSourceKey);
        } else {
            String msg = "数据源:" + dataSourceKey + "不存在";
            log.info(msg);
            throw new RuntimeException(msg);
        }
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 删除数据源
     */
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
