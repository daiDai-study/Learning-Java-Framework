package org.daistudy.springcloud.payment.mapper;

import org.apache.ibatis.annotations.Param;
import org.daistudy.springcloud.common.entity.Payment;

public interface PaymentMapper {

    int create(Payment payment);

    Payment getById(@Param("id") Long id);
}
