package org.daistudy.mybatis.onjava.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if(dataSource == null){
            dataSource = new PooledDataSource(
                    "com.microsoft.sqlserver.jdbc.SQLServerDriver",
                    "jdbc:sqlserver://10.177.97.180:1433;DatabaseName=plp_private",
                    "sqluser",
                    "123456!a");

        }
        return dataSource;
    }
}
