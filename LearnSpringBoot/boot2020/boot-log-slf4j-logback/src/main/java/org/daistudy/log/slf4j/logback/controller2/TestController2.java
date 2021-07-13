package org.daistudy.log.slf4j.logback.controller2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test2")
@Slf4j
public class TestController2 {

    @GetMapping("/1")
    public String test1(){
        log.trace("Trace 日志...");
        log.debug("Debug 日志...");
        log.info("Info 日志...");
        log.warn("Warn 日志...");
        log.error("Error 日志...");
        return "log";
    }
}
