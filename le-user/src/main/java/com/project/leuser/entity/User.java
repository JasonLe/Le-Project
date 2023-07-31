package com.project.leuser.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author whl
 * @Description:
 * @date 2023/7/12
 */
@Data
@TableName("user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String mobile;
    private String email;
    private String location;
    private Date createTime;
    private Date updateTime;
}
