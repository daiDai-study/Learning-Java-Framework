package org.daistudy.springcloud.stream.service.impl;

import cn.hutool.core.util.IdUtil;
import org.daistudy.springcloud.stream.service.MessageService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

@EnableBinding(Source.class)
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String msg = "serial: " + IdUtil.simpleUUID();
        Message<String> message = MessageBuilder.withPayload(msg).build();
        output.send(message);
        return msg;
    }
}
