package com.project.lecommon.exception;

import com.project.lecommon.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author whl
 * @Description:
 * @date 2023/7/14
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyException extends RuntimeException {
    private Integer code;
    private String msg;

    public MyException(String msg) {
        this.code = 600;
        this.msg = msg;
    }

    public MyException(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
    }
}
