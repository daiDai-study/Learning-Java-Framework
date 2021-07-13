package org.dai.study.quartz.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "任务日志实体对象", description = "任务日志实体对象")
public class SysJobLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** ID */
    @ApiModelProperty("任务日志ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long jobLogId;

    /** 任务名称 */
    @ApiModelProperty("任务名称")
    private String jobName;

    /** 任务组名 */
    @ApiModelProperty("任务组名")
    private String jobGroup;

    /** 调用目标字符串 */
    @ApiModelProperty("调用目标字符串")
    private String invokeTarget;

    /** 日志信息 */
    @ApiModelProperty("日志信息")
    private String jobMessage;

    /** 执行状态（0正常 1失败） */
    @ApiModelProperty("执行状态（0正常 1失败）")
    private String status;

    /** 异常信息 */
    @ApiModelProperty("异常信息")
    private String exceptionInfo;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("开始时间")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("结束时间")
    private Date endTime;

    @Override
    public String toString() {
        return "SysJobLog{" +
            "jobLogId=" + jobLogId +
            ", jobName='" + jobName + '\'' +
            ", jobGroup='" + jobGroup + '\'' +
            ", invokeTarget='" + invokeTarget + '\'' +
            ", jobMessage='" + jobMessage + '\'' +
            ", status='" + status + '\'' +
            ", exceptionInfo='" + exceptionInfo + '\'' +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            '}';
    }
}
