package org.daistudy.springbootshiro.controller;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;

@Component
public class ResultMap extends HashMap<String, Object> {
    private static final long serialVersionUID = 3187215695729366115L;

    public ResultMap() {
    }

    public ResultMap success() {
        this.put("result", "success");
        return this;
    }

    public ResultMap fail() {
        this.put("result", "fail");
        return this;
    }

    public ResultMap message(Object message) {
        this.put("message", message);
        return this;
    }

    public ResultMap data(Object data){
        this.put("data", data);
        return this;
    }
}
