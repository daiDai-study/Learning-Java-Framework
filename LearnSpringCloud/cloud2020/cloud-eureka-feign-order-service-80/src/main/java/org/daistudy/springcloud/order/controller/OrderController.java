package org.daistudy.springcloud.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.daistudy.springcloud.common.entity.CommonResult;
import org.daistudy.springcloud.common.entity.Payment;
import org.daistudy.springcloud.order.api.CloudPaymentService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class OrderController {

    @Resource
    private CloudPaymentService cloudPaymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        CommonResult result = cloudPaymentService.create(payment);
        log.info("rest结果：" + result);
        return result;
    }

    @GetMapping("/get/{id}")
    public CommonResult getById(@PathVariable("id") Long id){
        CommonResult result = cloudPaymentService.getById(id);
        log.info("rest结果：" + result);
        return result;
    }

    @GetMapping("/discovery")
    public CommonResult getDiscoveryClientList(){
        DiscoveryClient discoveryClient = this.discoveryClient;
        for (String service : discoveryClient.getServices()) {
            log.info("service: {}", service);
            for (ServiceInstance instance : discoveryClient.getInstances(service)) {
                log.info("\tinstance: {}, \thost: {}, \tport: {}, \turi: {}", instance.getInstanceId(), instance.getHost(), instance.getPort(), instance.getUri());
            }
        }
        return new CommonResult(200, "查询成功", discoveryClient);
    }

    @GetMapping("/get/lb")
    public String getIB(){
        String result = cloudPaymentService.getIB();
        log.info("rest结果：" + result);
        return result;
    }

    @GetMapping("/feign/timeout")
    public String getFeignTimeout(){
        String result = cloudPaymentService.feignTimeout();
        log.info("rest结果：" + result);
        return result;
    }
}
