package com.example.leblog.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author whl
 * @Description:
 * @date 2023/7/31
 */
@Builder
@Data
public class BlogResponse {
    private Integer blogId;
    private String title;
    private Date pubDate;
    private String digest;
}
