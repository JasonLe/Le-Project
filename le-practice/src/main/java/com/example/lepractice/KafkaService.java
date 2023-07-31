package com.example.lepractice;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author whl
 * @Description:
 * @date 2023/7/27
 */
@Service
public class KafkaService {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message) {
        kafkaTemplate.send("le-kafka-group", message);
    }
}
