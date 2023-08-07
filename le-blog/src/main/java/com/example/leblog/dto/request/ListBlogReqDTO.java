package com.example.leblog.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author whl
 * @Description:
 * @date 2023/8/2
 */
@Data
public class ListBlogReqDTO {
    private Integer pageNum;
    private Integer pageSize;

    @NotNull(message = "参数错误，缺少type")
    private Integer type;
}
