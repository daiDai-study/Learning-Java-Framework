package org.daistudy.springcloud.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.daistudy.springcloud.provider.service.HystrixCircuitBreakService;
import org.daistudy.springcloud.provider.service.HystrixFallbackService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/hystrix")
public class HystrixProviderController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private HystrixFallbackService hystrixFallbackService;

    @Resource
    private HystrixCircuitBreakService hystrixCircuitBreakService;

    @GetMapping("/fallback/ok")
    public String ok() {
        String ok = hystrixFallbackService.ok();
        log.info("{}-{}", serverPort, ok);
        return ok;
    }

    @GetMapping("/fallback/timeout")
    public String timeout() {
        String timeout = hystrixFallbackService.timeout();
        log.info("{}-{}", serverPort, timeout);
        return timeout;
    }

    @GetMapping("/fallback/exception")
    public String exception() {
        String exception = hystrixFallbackService.exception();
        log.info("{}-{}", serverPort, exception);
        return exception;
    }

    @GetMapping("/break/random/{id}")
    public String random(@PathVariable("id") Long id) {
        String result = hystrixCircuitBreakService.random(id);
        log.info("{}-{}", serverPort, result);
        return result;
    }
}
