package org.dai.study.quartz.springboot.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.dai.study.quartz.springboot.constant.ScheduleConstants;

@Data
@ApiModel(value = "任务实体对象", description = "任务实体对象")
public class SysJob extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 任务ID */
    @ApiModelProperty("任务ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long jobId;

    /** 任务名称 */
    @ApiModelProperty("任务名称")
    private String jobName;

    /** 任务组名 */
    @ApiModelProperty("任务组名")
    private String jobGroup;

    /** 调用目标字符串 */
    @ApiModelProperty(value = "调用目标字符串", name = "任务执行内容")
    private String invokeTarget;

    /** cron执行表达式 */
    @ApiModelProperty("cron执行表达式")
    private String cronExpression;

    /** cron计划策略 */
    @ApiModelProperty("cron计划策略")
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;

    /** 是否并发执行（0允许 1禁止） */
    @ApiModelProperty("是否并发执行（0允许 1禁止）")
    private String concurrent;

    /** 任务状态（0正常 1暂停） */
    @ApiModelProperty("任务状态（0正常 1暂停）")
    private String status;

    @Override
    public String toString() {
        return "SysJob{" +
            "jobId=" + jobId +
            ", jobName='" + jobName + '\'' +
            ", jobGroup='" + jobGroup + '\'' +
            ", invokeTarget='" + invokeTarget + '\'' +
            ", cronExpression='" + cronExpression + '\'' +
            ", misfirePolicy='" + misfirePolicy + '\'' +
            ", concurrent='" + concurrent + '\'' +
            ", status='" + status + '\'' +
            '}';
    }
}
