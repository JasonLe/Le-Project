package com.example.leblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author whl
 * @Description:
 * @date 2023/8/2
 */
@Data
@Builder
@TableName("blog")
@NoArgsConstructor
@AllArgsConstructor
public class BlogEntity {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "title")
    private String title;

    @TableField(value = "digest")
    private String digest;
    
    @TableField(value = "content")
    private String content;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;
}
