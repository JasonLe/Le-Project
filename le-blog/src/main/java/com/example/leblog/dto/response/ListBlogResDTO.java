package com.example.leblog.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author whl
 * @Description:
 * @date 2023/8/7
 */
@Data
public class ListBlogResDTO implements Serializable {
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
