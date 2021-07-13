package org.daistudy.spring.boot.orm.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResult<T> {

    private Integer code;

    private String message;

    private T data;

    private ApiResult(ApiResultCode apiResultCode, T data){
        this.code = apiResultCode.getCode();
        this.message = apiResultCode.getMessage();
        this.data = data;
    }

    public static <T> ApiResult ofSuccess(T data){
        return new ApiResult(ApiResultCode.SUCCESS, data);
    }

    public static <T> ApiResult ofFail(){
        return new ApiResult(ApiResultCode.FAIL, null);
    }

    public static <T> ApiResult ofFail(String message){
        return new ApiResult(500, message, null);
    }
}
