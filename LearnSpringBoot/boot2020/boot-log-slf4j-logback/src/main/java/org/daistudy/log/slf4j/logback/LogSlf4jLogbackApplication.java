package org.daistudy.log.slf4j.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class LogSlf4jLogbackApplication {
    public static void main(String[] args) {
        log.trace("Trace 日志...");
        log.debug("Debug 日志...");
        log.info("Info 日志...");
        log.warn("Warn 日志...");
        log.error("Error 日志...");
        SpringApplication.run(LogSlf4jLogbackApplication.class, args);
    }
}
