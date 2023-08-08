package com.example.leblog.utils;

import com.example.leblog.dto.message.Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author whl
 * @Description:
 * @date 2023/8/8
 */
@Component
public class MQUtils {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.producer.sendMessageTimeout}")
    private Integer messageTimeOut;

    @Value("${rocketmq.topic}")
    private String TOPIC;

    /**
     * 普通发送
     **/
    public void send(Message message) {
        rocketMQTemplate.convertAndSend(TOPIC + ":tag1", message);
    }

    /**
     * 同步发送
     **/
    public SendResult sendSync(Message message) {
        return rocketMQTemplate.syncSend(TOPIC + ":tag1", message);
    }

    /**
     * 异步发送
     **/
    public void sendAsync(Message message) {
        rocketMQTemplate.asyncSend(TOPIC + ":tag1", message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

            }

            @Override
            public void onException(Throwable throwable) {

            }
        });
    }

    /**
     * 发送延时消息（上面的发送同步消息，delayLevel的值就为0，因为不延时）
     * 在start版本中 延时消息一共分为18个等级分别为：1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     */
    public void sendDelayMsg(Message message, int delayLevel) {
        rocketMQTemplate.syncSend(TOPIC + ":tag2", MessageBuilder.withPayload(message).build(), messageTimeOut, delayLevel);
    }

    /**
     * 发送单向消息（只负责发送消息，不等待应答，不关心发送结果，如日志）
     */
    public void sendOneWayMsg(Message message) {
        rocketMQTemplate.sendOneWay(TOPIC + ":tag3", MessageBuilder.withPayload(message).build());
    }

    /**
     * 发送带tag的消息
     */
    public SendResult sendTagMsg(Message message, String tag) {
        return rocketMQTemplate.syncSend(TOPIC + tag, MessageBuilder.withPayload(message).build());
    }
}
