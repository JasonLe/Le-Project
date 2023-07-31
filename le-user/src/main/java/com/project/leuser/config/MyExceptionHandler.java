package com.project.leuser.config;

import com.project.lecommon.exception.MyException;
import com.project.lecommon.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author whl
 * @Description:
 * @date 2023/7/14
 */
@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler({MyException.class})
    public R<String> handleMyException(MyException e) {
        log.error("错误：{}", e.getMsg());
        return R.error(e.getCode(), e.getMsg());
    }
}
