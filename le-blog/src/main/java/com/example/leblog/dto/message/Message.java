package com.example.leblog.dto.message;

import lombok.*;

import java.io.Serializable;

/**
 * @author whl
 * @Description:
 * @date 2023/8/8
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String id;
    private String date;
    private Object data;
}
