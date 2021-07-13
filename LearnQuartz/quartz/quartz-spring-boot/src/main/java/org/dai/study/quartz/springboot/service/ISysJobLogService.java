package org.dai.study.quartz.springboot.service;

import org.dai.study.quartz.springboot.entity.SysJobLog;

import java.util.List;

/**
 * 定时任务调度日志信息信息 服务层
 * 
 * @author ruoyi
 */
public interface ISysJobLogService
{
    /**
     * 获取quartz调度器日志的计划任务
     * 
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    List<SysJobLog> listFuzzy(SysJobLog jobLog);

    /**
     * 通过调度任务日志ID查询调度信息
     * 
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    SysJobLog getById(Long jobLogId);

    /**
     * 新增任务日志
     * 
     * @param jobLog 调度日志信息
     */
    void add(SysJobLog jobLog);

    /**
     * 批量删除调度日志信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int removeByIds(String ids);

    /**
     * 删除任务日志
     * 
     * @param jobId 调度日志ID
     * @return 结果
     */
    int removeById(Long jobId);
    
    /**
     * 清空任务日志
     */
    void clear();
}
