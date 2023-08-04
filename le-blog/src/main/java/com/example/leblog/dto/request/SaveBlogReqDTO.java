package com.example.leblog.dto.request;

import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;
import lombok.Data;
import lombok.ToString;

/**
 * @author whl
 * @Description:
 * @date 2023/8/2
 */
@Data
@ToString
public class SaveBlogReqDTO {
    private String blogId;
    private String title;
    private String content;
}