package com.example.lepractice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whl
 * @Description:
 * @date 2023/7/27
 */
@RequestMapping("/kafka")
@RestController
public class KafkaController {

    @RequestMapping("/send")
    public void sendMessage() {
        
    }
}
