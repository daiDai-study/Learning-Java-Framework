package org.daistudy.springbootshiro.controller;

import org.apache.shiro.ShiroException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @Autowired
    private ResultMap resultMap;

    @ExceptionHandler(ShiroException.class)
    public ResultMap handleShiroException(Exception ex){
        return resultMap.fail().message(ex.getMessage());
    }
}
