package com.apt612.depaybackend.controller.security.authentification;

import com.apt612.depaybackend.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;


public class TokenUtils {
    public static String getToken(User user) {
        return JWT.create().withExpiresAt(
                new Date(System.currentTimeMillis() + Constants.EXPIRE_TIME)).withAudience(user.getId())
                .sign(Algorithm.HMAC256(Constants.SECRET));
    }

    public static boolean verifyAuthen(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256(Constants.SECRET);
            JWTVerifier jwtVerifier=JWT.require(algorithm).build();
            DecodedJWT decodedJWT=jwtVerifier.verify(token);
            return true;
        }catch (JWTVerificationException jwtVerificationException){
            jwtVerificationException.printStackTrace();
            return false;
        }
    }

}
