package org.daistudy.springframework.jdbc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExceptionService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void exception(){
        throw new NullPointerException("空");
    }
}
