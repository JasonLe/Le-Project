package com.example.leblog.consumer;

import com.example.leblog.dto.message.Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author whl
 * @Description:
 * @date 2023/8/8
 */
@Component
@RocketMQMessageListener(
        topic = "le-topic",
        consumerGroup = "le-consumer-group"
)
public class Consumer implements RocketMQListener<Message> {
    @Override
    public void onMessage(Message s) {
        System.out.println(s);
    }
}
