package com.project.leuser.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author whl
 * @Description:
 * @date 2023/7/12
 */
@Data
public class LoginDTO {

    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

    @NotNull(message = "账号不能为空")
    private String email;
}
