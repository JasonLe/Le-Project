package com.project.leuser.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author whl
 * @Description:
 * @date 2023/7/14
 */
@Data
public class RegisterDTO {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

    private String mobile;

    @NotNull(message = "邮箱不能为空")
    private String email;

    private String location;
}
