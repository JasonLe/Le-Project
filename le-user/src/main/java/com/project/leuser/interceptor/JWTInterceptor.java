package com.project.leuser.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.project.lecommon.enums.ExceptionEnum;
import com.project.lecommon.exception.MyException;
import com.project.leuser.annotation.AuthType;
import com.project.leuser.annotation.NeedAuth;
import com.project.leuser.utils.JWTUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author whl
 * @Description:
 * @date 2023/7/14
 */
public class JWTInterceptor implements HandlerInterceptor {

    private static final String LE_TOKEN = "Le-Token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        NeedAuth[] annotationOnClass = ((HandlerMethod) handler).getBeanType().getAnnotationsByType(NeedAuth.class);
        NeedAuth annotationOnMethod = ((HandlerMethod) handler).getMethodAnnotation(NeedAuth.class);

        /**
         * 判断逻辑：
         * 如果方法上有注解，优先方法，否则使用类上的注解规则
         * 如果都没有注解，则默认可以放行
         **/
        if (annotationOnMethod == null && annotationOnClass.length == 0) {
            return true;
        }

        if (annotationOnMethod != null) {
            if (AuthType.UNNECESSARY.equals(annotationOnMethod.value())){
                return true;
            }
        }else {
            NeedAuth needAuth = annotationOnClass[0];
            if (AuthType.UNNECESSARY.equals(needAuth.value())){
                return true;
            }
        }

        String token = request.getHeader(LE_TOKEN);
        if (StrUtil.isBlank(token)) {
            throw new MyException(ExceptionEnum.NO_LOGIN);
        }
        try {
            JWTUtil.verifyToken(token);
        } catch (TokenExpiredException e) {
            throw new MyException(ExceptionEnum.TOKEN_EXPIRED);
        } catch (JWTDecodeException e) {
            throw new MyException(ExceptionEnum.TOKEN_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
