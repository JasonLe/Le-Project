package com.example.leblog.controller;

import com.example.leblog.dto.message.Message;
import com.example.leblog.utils.MQUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whl
 * @Description:
 * @date 2023/8/8
 */
@RestController
public class MqController {

    @Autowired
    private MQUtils mqUtils;

    @RequestMapping("/send")
    public void send() {
        Message data = Message.builder().id("1").date("2023-08-08").data("这里是data").build();
        mqUtils.sendSync(data);
    }
}
