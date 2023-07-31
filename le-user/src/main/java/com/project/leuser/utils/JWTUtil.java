package com.project.leuser.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.leuser.entity.User;

import java.util.Calendar;
import java.util.Map;

/**
 * @author whl
 * @Description:
 * @date 2023/7/12
 */
public class JWTUtil {

    private static final String TOKEN = "thisismysecret";

    private static final int EXPIRE_MINUTE = 10;

    /**
     * 生成token
     *
     * @param user 传入user用于payload
     * @return 返回token
     * JWT 最后的形式就是个字符串，它由头部、载荷与签名这三部分组成，中间以「.」分隔
     * 头部：签名算法
     * 载荷：包含数据
     * 签名：密钥
     */
    public static String generateToken(User user) {
        JWTCreator.Builder builder = JWT.create();
        // 设置载荷
        builder.withClaim("id",user.getId());
        builder.withClaim("username",user.getUsername());
        builder.withClaim("mobile",user.getMobile());
        builder.withClaim("location",user.getLocation());

        // 设置过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, EXPIRE_MINUTE);
        builder.withExpiresAt(calendar.getTime());
        return builder.sign(Algorithm.HMAC256(TOKEN));
    }

    /**
     * 验证token
     */
    public static void verifyToken(String token) {
        JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }

    /**
     * 获取token中payload
     */
    public static DecodedJWT getPayload(String token) {
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }
}
