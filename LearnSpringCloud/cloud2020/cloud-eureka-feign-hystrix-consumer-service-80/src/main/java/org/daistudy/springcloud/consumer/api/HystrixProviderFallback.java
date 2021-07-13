package org.daistudy.springcloud.consumer.api;

import org.springframework.stereotype.Component;

@Component
public class HystrixProviderFallback implements HystrixProviderService {
    @Override
    public String ok() {
        return "fallback--ok";
    }

    @Override
    public String timeout() {
        return "fallback--timeout";
    }

    @Override
    public String exception() {
        return "fallback--exception";
    }
}
