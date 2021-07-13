package org.daistudy.springcloud.order.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface IOrderLoadBalanced {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
