package org.daistudy.springcloud.sentinel.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class DefalultBlockHandler {
    public static String blockHandler(String p1, String p2, BlockException ex){
        return "blockHandler--defalult";
    }
}
