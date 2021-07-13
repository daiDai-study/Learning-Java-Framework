package org.daistudy.springcloud.provider.service;

public interface HystrixFallbackService {
    String ok();
    String timeout();
    String exception();
}
