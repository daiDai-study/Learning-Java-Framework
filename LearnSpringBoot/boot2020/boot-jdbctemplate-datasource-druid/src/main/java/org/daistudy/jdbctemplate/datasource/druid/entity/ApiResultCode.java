package org.daistudy.jdbctemplate.datasource.druid.entity;

import lombok.Getter;

@Getter
public enum ApiResultCode {

    SUCCESS(200, "success"),

    FAIL(500, "fail");

    private Integer code;

    private String message;

    private ApiResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
