package org.dai.study.quartz.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "基础实体对象", description = "基础实体对象")
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @ApiModelProperty("更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;
}
