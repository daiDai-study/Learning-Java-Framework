package org.daistudy.springcloud.order.api;

import org.daistudy.springcloud.common.entity.CommonResult;
import org.daistudy.springcloud.common.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE", path = "/payment")
public interface CloudPaymentService {

    @GetMapping("/feign/timeout")
    String feignTimeout();

    @GetMapping("/get/lb")
    String getIB();

    @GetMapping("/get/{id}")
    CommonResult getById(@PathVariable("id") Long id);

    @PostMapping("/create")
    CommonResult create(@RequestBody Payment payment);
}
