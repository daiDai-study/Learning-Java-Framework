package org.daistudy.springcloud.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.daistudy.springcloud.sentinel.block.DefalultBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String name = Thread.currentThread().getName();
        log.info(name);
        return name;
    }

    @GetMapping("/testB")
    public String testB(){
        String name = Thread.currentThread().getName();
        log.info(name);
        int i = 10 / 0;
        return name;
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandlerClass = DefalultBlockHandler.class, blockHandler = "blockHandler")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){
        String name = Thread.currentThread().getName();
        log.info(name);
        return name;
    }

    public String blockHandler(String p1, String p2, BlockException ex){
        return "blockHandler";
    }
}
