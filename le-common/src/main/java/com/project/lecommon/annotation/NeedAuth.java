package com.project.lecommon.annotation;

import java.lang.annotation.*;

/**
 * 判断请求是否需要登录认证
 * @author whl
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedAuth {

    AuthType value();
}
