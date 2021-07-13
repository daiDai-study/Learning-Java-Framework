package org.dai.study.quartz.springboot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.dai.study.quartz.springboot.constant.Constants;

import java.io.Serializable;

/**
 * 接口返回数据格式
 */
@Data
@ApiModel(value = "接口返回对象", description = "接口返回对象")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 返回代码
     */
    @ApiModelProperty(value = "返回代码")
    private Integer code = 0;
    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "返回处理消息")
    private String message = "操作成功！";
    /**
     * 返回数据对象 data
     */
    @ApiModelProperty(value = "返回数据对象")
    private T result;
    /**
     * 成功标志
     */
    @ApiModelProperty(value = "成功标志")
    private boolean success = true;
    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();

    private Result() { }

    private Result error(String message) {
        this.message = message;
        this.code = Constants.SC_INTERNAL_SERVER_ERROR_500;
        this.success = false;
        return this;
    }

    private Result<T> success(T data) {
        this.result = data;
        return this;
    }

    private Result<T> success() {
        return this;
    }

    private Result noAuth() {
        this.message = "权限限制";
        this.code = Constants.SC_NO_AUTHZ;
        this.success = false;
        return this;
    }

    public static Result errorInstance(String message) {
        return new Result<>().error(message);
    }

    public static Result noAuthInstance() {
        return new Result<>().noAuth();
    }

    public static <T> Result<T> successInstance(T data) {
        return new Result<T>().success(data);
    }

    public static Result successInstance() {
        return new Result<>().success();
    }
}
