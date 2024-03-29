package org.dai.study.quartz.springboot.task;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println("执行多参方法： 字符串类型"  + s + "，布尔类型" + b + "，长整型" + l + "，浮点型" + d + "，整形" + i);
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }
}
