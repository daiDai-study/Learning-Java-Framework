package org.daistudy.springcloud.consumer.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "CLOUD-HYSTRIX-PROVIDER-SERVICE", path = "/hystrix", fallback = HystrixProviderFallback.class)
public interface HystrixProviderService {
    @GetMapping("/fallback/ok")
    String ok();

    @GetMapping("/fallback/timeout")
    String timeout();

    @GetMapping("/fallback/exception")
    String exception();
}
