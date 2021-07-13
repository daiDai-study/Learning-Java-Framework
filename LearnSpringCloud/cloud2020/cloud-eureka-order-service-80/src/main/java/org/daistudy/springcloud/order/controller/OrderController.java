package org.daistudy.springcloud.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.daistudy.springcloud.common.entity.CommonResult;
import org.daistudy.springcloud.common.entity.Payment;
import org.daistudy.springcloud.order.lb.IOrderLoadBalanced;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class OrderController {

//    private static final String PAYMENT_URL = "http://localhost:8001/payment";
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/payment";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private IOrderLoadBalanced orderLoadBalanced;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        CommonResult result = restTemplate.postForObject(PAYMENT_URL + "/create", payment, CommonResult.class);
        log.info("rest结果：" + result);
        return result;
    }

    @GetMapping("/get/{id}")
    public CommonResult getById(@PathVariable("id") Long id){
        CommonResult result = restTemplate.getForObject(PAYMENT_URL + "/get/" + id, CommonResult.class);
        log.info("rest结果：" + result);
        return result;
    }

    @PostMapping("/create-entity")
    public CommonResult createEntity(@RequestBody Payment payment){
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/create", payment, CommonResult.class);
        return entityToObject(entity);
    }

    @GetMapping("/get-entity/{id}")
    public CommonResult getEntityById(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/get/" + id, CommonResult.class);
        return entityToObject(entity);
    }

    private CommonResult entityToObject(ResponseEntity<CommonResult> entity){
        if (entity.getStatusCode().is2xxSuccessful()) {
            CommonResult result = entity.getBody();
            log.info("rest结果：" + result);
            return result;
        }else{
            String error = "rest 服务调用失败：\n"
                + "\t状态码：" + entity.getStatusCodeValue() + "\n"
                + "\t响应头：" + entity.getHeaders()+ "\n";
            CommonResult result = new CommonResult(500, error);
            log.info("rest结果：" + result);
            return result;
        }
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
        List<ServiceInstance> instances = this.discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (CollectionUtils.isEmpty(instances)) {
            return null;
        }
        ServiceInstance instance = orderLoadBalanced.instance(instances);
        return restTemplate.getForObject(instance.getUri() + "/payment/get/lb", String.class);
    }
}
