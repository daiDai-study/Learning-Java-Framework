package org.dai.study.quartz.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dai.study.quartz.springboot.entity.SysJobLog;
import org.dai.study.quartz.springboot.model.Result;
import org.dai.study.quartz.springboot.service.ISysJobLogService;
import org.dai.study.quartz.springboot.service.ISysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 调度日志操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor/jobLog")
@Api(tags = "任务日志")
public class SysJobLogController {

    @Autowired
    private ISysJobService jobService;

    @Autowired
    private ISysJobLogService jobLogService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation("任务日志列表")
    public Result<List<SysJobLog>> list()
    {
        List<SysJobLog> list = jobLogService.listFuzzy(null);
        return Result.successInstance(list);
    }

    @PostMapping("/remove")
    @ResponseBody
    @ApiOperation("批量删除")
    public Result remove(@RequestBody Map<String, String> map)
    {
        String ids = map.get("ids");
        jobLogService.removeByIds(ids);
        return Result.successInstance();
    }

    @GetMapping("/detail/{jobLogId}")
    @ApiOperation("任务日志详情")
    public Result detail(@PathVariable("jobLogId") Long jobLogId)
    {
        SysJobLog sysJobLog = jobLogService.getById(jobLogId);
        return Result.successInstance(sysJobLog);
    }

    @PostMapping("/clean")
    @ResponseBody
    @ApiOperation("清空任务日志")
    public Result clean()
    {
        jobLogService.clear();
        return Result.successInstance();
    }
}
