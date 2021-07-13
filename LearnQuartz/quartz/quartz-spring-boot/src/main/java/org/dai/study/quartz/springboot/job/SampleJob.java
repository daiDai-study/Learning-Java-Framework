package org.dai.study.quartz.springboot.job;

import lombok.extern.slf4j.Slf4j;
import org.dai.study.quartz.springboot.service.HelloService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SampleJob extends QuartzJobBean {
    private HelloService helloService;
    private String name;

    @Autowired
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(this.helloService.hello(this.name));
    }
}
