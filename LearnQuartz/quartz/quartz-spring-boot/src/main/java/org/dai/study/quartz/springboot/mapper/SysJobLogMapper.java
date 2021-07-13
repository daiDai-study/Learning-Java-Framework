package org.dai.study.quartz.springboot.mapper;

import org.dai.study.quartz.springboot.entity.SysJobLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysJobLogMapper {

    /**
     * 获取quartz调度器日志的计划任务
     *
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    public List<SysJobLog> listFuzzy(SysJobLog jobLog);

    /**
     * 查询所有调度任务日志
     *
     * @return 调度任务日志列表
     */
    public List<SysJobLog> list();

    /**
     * 通过调度任务日志ID查询调度信息
     *
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    public SysJobLog getById(Long jobLogId);

    /**
     * 新增任务日志
     *
     * @param jobLog 调度日志信息
     * @return 结果
     */
    public int add(SysJobLog jobLog);

    /**
     * 批量删除调度日志信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int removeByIds(String[] ids);

    /**
     * 删除任务日志
     *
     * @param jobId 调度日志ID
     * @return 结果
     */
    public int removeById(Long jobId);

    /**
     * 清空任务日志
     */
    public void clear();
}
