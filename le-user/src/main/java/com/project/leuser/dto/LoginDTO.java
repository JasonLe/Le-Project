package com.project.leuser.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author whl
 * @Description:
 * @date 2023/7/12
 */
@Data
public class LoginDTO {

    @NotNull(message = "用户名不能为空")
    private String userName;

    @NotNull(message = "密码不能为空")
    private String passWord;

    private String leToken;
}
