package org.daistudy.springcloud.payment.service.impl;

import org.daistudy.springcloud.common.entity.Payment;
import org.daistudy.springcloud.payment.mapper.PaymentMapper;
import org.daistudy.springcloud.payment.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public int create(Payment payment) {
        return paymentMapper.create(payment);
    }

    @Override
    public Payment getById(Long id) {
        return paymentMapper.getById(id);
    }
}
