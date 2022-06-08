package com.cskt.itripauth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Jwt工具类，用于生成和校验jwt
 *
 */
public class JWTUtil {

    /**过期时间为半小时*/
    private static final long EXPRIE_TIME = 30*60*1000;//30分钟
    /**加密时的密钥，不能泄露*/
    private static final String secret = "A8B7ERF-88AC-3CD2-E804-661A0C961F29";

    /**
     * 校验用户名和密码
     * @param token
     * @param
     * @return
     */
    public static boolean verify(String token) {
        try {
            // 加密方式必须与生成token时的加密方式一致
            Algorithm algorithm = Algorithm.HMAC512(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 获取token中的信息无需secret解密也能获得
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


//    public static String getUserId(String token) {
//        try {
//            DecodedJWT jwt = JWT.decode(token);
//            return jwt.getClaim("userId").asString();
//        } catch (JWTDecodeException e) {
//            return null;
//        }
//    }


//    /**
//     * 生成JWTToken自定义 ItripUserDetails 用于封装用户信息
//     * @param username
//     * @return
//     * @throws UnsupportedEncodingException
//     */
//    public static String sign(String username,String userId) throws
//            UnsupportedEncodingException {
//        Date date = new Date(System.currentTimeMillis()+EXPRIE_TIME);
//        Algorithm algorithm = Algorithm.HMAC512(secret);
//        // 附带username信息
//        return JWT.create()
//                .withClaim("username", username)
//                .withClaim("userId", userId)
//                .withExpiresAt(date)
//                .sign(algorithm);
//    }
    /**
     * 生成JWTToken自定义 ItripUserDetails 用于封装用户信息
     * @param username
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String sign(String username,String userid) throws
            UnsupportedEncodingException {
        Date date = new Date(System.currentTimeMillis()+EXPRIE_TIME);
        Algorithm algorithm = Algorithm.HMAC512(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                .withClaim("userid", userid)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 获取token中的信息userid
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userid").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
