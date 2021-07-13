package org.dai.study.quartz.springboot.service.impl;

import org.dai.study.quartz.springboot.entity.SysJobLog;
import org.dai.study.quartz.springboot.mapper.SysJobLogMapper;
import org.dai.study.quartz.springboot.service.ISysJobLogService;
import org.dai.study.quartz.springboot.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定时任务调度日志信息 服务层
 * 
 * @author ruoyi
 */
@Service
public class SysJobLogServiceImpl implements ISysJobLogService
{
    @Autowired
    private SysJobLogMapper jobLogMapper;

    /**
     * 获取quartz调度器日志的计划任务
     * 
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    @Override
    public List<SysJobLog> listFuzzy(SysJobLog jobLog)
    {
        if(jobLog == null){
            return jobLogMapper.list();
        }
        return jobLogMapper.listFuzzy(jobLog);
    }

    /**
     * 通过调度任务日志ID查询调度信息
     * 
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    @Override
    public SysJobLog getById(Long jobLogId)
    {
        return jobLogMapper.getById(jobLogId);
    }

    /**
     * 新增任务日志
     * 
     * @param jobLog 调度日志信息
     */
    @Override
    public void add(SysJobLog jobLog)
    {
        jobLogMapper.add(jobLog);
    }

    /**
     * 批量删除调度日志信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int removeByIds(String ids)
    {
        return jobLogMapper.removeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除任务日志
     * 
     * @param jobId 调度日志ID
     */
    @Override
    public int removeById(Long jobId)
    {
        return jobLogMapper.removeById(jobId);
    }

    /**
     * 清空任务日志
     */
    @Override
    public void clear()
    {
        jobLogMapper.clear();
    }
}
