package com.example.leblog.dto.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author whl
 * @Description:
 * @date 2023/8/2
 */
@Data
@ToString
public class SaveBlogReqDTO {
    private String blogId;

    @NotNull(message = "标题不能为空")
    private String title;

    @NotNull(message = "内容不能为空")
    private String content;
}
