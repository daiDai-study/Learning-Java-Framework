package org.daistudy.springcloud.provider.service;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

public interface HystrixCircuitBreakService {
    String random(Long id);
}
