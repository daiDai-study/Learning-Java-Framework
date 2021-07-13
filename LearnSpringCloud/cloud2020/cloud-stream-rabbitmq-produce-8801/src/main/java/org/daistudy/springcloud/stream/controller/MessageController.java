package org.daistudy.springcloud.stream.controller;

import lombok.extern.slf4j.Slf4j;
import org.daistudy.springcloud.stream.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @GetMapping("/send")
    public String send(){
        String send = messageService.send();
        log.info(send);
        return send;
    }
}
