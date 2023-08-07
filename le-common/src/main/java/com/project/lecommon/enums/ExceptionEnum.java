package com.project.lecommon.enums;

import lombok.Getter;

/**
 * @author whl
 * @Description:
 * @date 2023/7/14
 */
@Getter
public enum ExceptionEnum {

    /**
     * 系统错误
     **/
    ERROR(600, "系统错误"),

    NO_LOGIN(601, "请登录后重试"),

    TOKEN_EXPIRED(602, "登录已过期"),

    TOKEN_ERROR(603, "token有误"),

    REGISTER_ERROR(604, "注册失败"),

    USER_NOT_EXISTS(605, "用户不存在"),

    ACCOUNT_PWD_ERROR(606, "账号密码错误"),
    ;

    private final Integer code;
    private final String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
