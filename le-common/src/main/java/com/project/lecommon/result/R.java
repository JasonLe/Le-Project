package com.project.lecommon.result;

import com.project.lecommon.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author whl
 * @Description:
 * @date 2023/7/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> R<T> success() {
        return R.success(null);
    }

    public static <T> R<T> success(T data) {
        return new R<T>(200, "success", data);
    }

    public static <T> R<T> error(Integer code, String msg) {
        return new R<T>(code, msg, null);
    }

    public static <T> R<T> error(ExceptionEnum exceptionEnum) {
        return new R<T>(exceptionEnum.getCode(), exceptionEnum.getMsg(), null);
    }

    public static <T> R<T> isSuccess(Boolean bool, ExceptionEnum exceptionEnum) {
        return bool ? R.success() : R.error(exceptionEnum);
    }
}
