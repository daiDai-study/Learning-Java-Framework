package org.daistudy.spring.boot.datasource.dynamic.routing.entity;

import lombok.Data;

@Data
public class DataSourceConfig {
    private Long id;
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String database;

    public String getJdbcUrl(){
        return  "jdbc:mysql://" + this.getHost() + ":" + this.getPort() + "/" + this.getDatabase() + "?useSSL=false";
    }

    public String getMysqlDriver(){
        return "com.mysql.jdbc.Driver";
    }
}
