package org.daistudy.springcloud.demo.member.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "book", fallback = BookClient.BookClientFallback.class)
public interface BookClient {

    @GetMapping("port")
    String getPort();

    @Component
    static class BookClientFallback implements BookClient{
        @Override
        public String getPort() {
            return "系统正忙，请稍后再试";
        }
    }
}
