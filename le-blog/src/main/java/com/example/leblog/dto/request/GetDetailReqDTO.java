package com.example.leblog.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author whl
 * @Description:
 * @date 2023/8/2
 */
@Data
public class GetDetailReqDTO {
    @NotNull(message = "请选择文章")
    private String blogId;
}
