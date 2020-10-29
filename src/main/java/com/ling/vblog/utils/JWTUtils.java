package com.ling.vblog.utils;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    private static final String SIGN="sawmjshdkjah123124%^&*()#@@!";

    /*生成token   header.payload.sing
    * */
    public static String getToken(Map<String, String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));

    }

    /*
    验证token
    * */
    public static DecodedJWT verifyToken(String token){
       return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }


}
