package org.daistudy.springcloud.order.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class OrderLoadBalanced implements IOrderLoadBalanced{

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    private final int getNextAndIncrement(){
        int current, next;

        do{
            current = atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : (current + 1);
        }while(!atomicInteger.compareAndSet(current, next));

        log.info("第{}次访问", next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getNextAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
