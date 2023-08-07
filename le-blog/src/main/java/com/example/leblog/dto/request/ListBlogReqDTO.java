package com.example.leblog.dto.request;

import lombok.Data;

/**
 * @author whl
 * @Description:
 * @date 2023/8/2
 */
@Data
public class ListBlogReqDTO {
    private Integer pageNum;
    private Integer pageSize;
    private Integer type;
}
