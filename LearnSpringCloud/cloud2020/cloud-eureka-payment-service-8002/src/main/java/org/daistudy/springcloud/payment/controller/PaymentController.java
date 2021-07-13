package org.daistudy.springcloud.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.daistudy.springcloud.common.entity.CommonResult;
import org.daistudy.springcloud.common.entity.Payment;
import org.daistudy.springcloud.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info(serverPort + "-插入数据库结果：{}", result);

        if(result > 0){
            log.info(serverPort + "-插入成功后， 支付id：{}", payment.getId());
            return new CommonResult(200, serverPort + "-插入成功", result);
        }else{
            return new CommonResult(500, serverPort + "-插入失败");
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult getById(@PathVariable("id") Long id){
        Payment result = paymentService.getById(id);

        if(result != null){
            log.info(serverPort + "-查询数据库结果：{}，O(∩_∩)O", result);
            return new CommonResult(200, serverPort + "-查询成功", result);
        }else{
            return new CommonResult(500, serverPort + "-查询失败");
        }
    }

    @GetMapping("/get/lb")
    public String getIB(){
        return serverPort;
    }

    @GetMapping("/feign/timeout")
    public String feignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
