package org.daistudy.springcloud.payment.service;

import org.daistudy.springcloud.common.entity.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment getById(Long id);
}
