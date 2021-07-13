package org.dai.study.quartz.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dai.study.quartz.springboot.entity.SysJob;
import org.dai.study.quartz.springboot.exception.TaskException;
import org.dai.study.quartz.springboot.model.Result;
import org.dai.study.quartz.springboot.service.ISysJobService;
import org.dai.study.quartz.springboot.util.CronUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 调度任务信息操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor/job")
@Api(tags = "任务")
public class SysJobController {

    @Autowired
    private ISysJobService jobService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation("任务列表")
    public Result<List<SysJob>> list()
    {
        List<SysJob> list = jobService.listFuzzy(null);
        return Result.successInstance(list);
    }

    @PostMapping("/remove")
    @ResponseBody
    @ApiOperation("批量删除")
    public Result remove(@RequestBody Map<String, String> map) throws SchedulerException
    {
        String ids = (String) map.get("ids");
        jobService.removeByIds(ids);
        return Result.successInstance();
    }

    @GetMapping("/detail/{jobId}")
    @ApiOperation("任务详情")
    public Result<SysJob> detail(@PathVariable("jobId") Long jobId)
    {
        SysJob sysJob = jobService.getById(jobId);
        return Result.successInstance(sysJob);
    }

    /**
     * 任务调度状态修改
     */
    @PostMapping("/changeStatus")
    @ResponseBody
    @ApiOperation("任务调度状态修改")
    public Result<SysJob> changeStatus(@RequestBody SysJob job) throws SchedulerException
    {
        SysJob newJob = jobService.getById(job.getJobId());
        newJob.setStatus(job.getStatus());
        int i = jobService.changeStatus(newJob);
        return Result.successInstance(newJob);
    }

    /**
     * 任务调度立即执行一次
     */
    @PostMapping("/run")
    @ResponseBody
    @ApiOperation("任务调度立即执行一次")
    public Result run(@RequestBody SysJob job) throws SchedulerException
    {
        jobService.run(job.getJobId());
        return Result.successInstance();
    }

    /**
     * 新增保存调度
     */
    @PostMapping("/add")
    @ResponseBody
    @ApiOperation("新增任务")
    public Result add(@RequestBody SysJob job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression()))
        {
            return Result.errorInstance("cron表达式不正确");
        }
        return Result.successInstance(jobService.add(job));
    }

    /**
     * 修改保存调度
     */
    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation("修改任务")
    public Result edit(@RequestBody SysJob job) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(job.getCronExpression()))
        {
            return Result.errorInstance("cron表达式不正确");
        }
        return Result.successInstance(jobService.update(job));
    }

    /**
     * 校验cron表达式是否有效
     */
    @ApiOperation("校验cron表达式是否有效")
    @PostMapping("/checkCronExpressionIsValid")
    @ResponseBody
    public boolean checkCronExpressionIsValid(@RequestBody SysJob job)
    {
        return jobService.checkCronExpressionIsValid(job.getCronExpression());
    }
}
