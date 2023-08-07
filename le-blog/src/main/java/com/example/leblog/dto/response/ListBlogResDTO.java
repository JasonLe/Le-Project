package com.example.leblog.dto.response;

import lombok.Data;

/**
 * @author whl
 * @Description:
 * @date 2023/8/7
 */
@Data
public class ListBlogResDTO {
    private Integer id;

    private String title;

    private String digest;

    private String content;

    private Integer status;

    private Integer type;

    private String image;

    private Integer praise;

    private String createTime;

    private String updateTime;
}
