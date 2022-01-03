package com.example.wsscarapi.tool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class Token {
    public String getAccessToken() {
        JwtBuilder jwtBuilder = Jwts.builder();

        long nowMillis = System.currentTimeMillis();
        // 7个官方Payload字段
        jwtBuilder.setId("1.0.0"); // 编号/版本
        jwtBuilder.setIssuer("wss_car"); // 发行人
        jwtBuilder.setSubject("wss_car"); // 主题
        jwtBuilder.setAudience("everybody"); // 受众
        jwtBuilder.setIssuedAt(new Date(nowMillis)); // 签发时间
        jwtBuilder.setNotBefore(new Date(nowMillis)); // 生效时间
        jwtBuilder.setExpiration(new Date(nowMillis + (24 * 60 * 60 * 1000))); // 失效时间

        // 用户自定义字段
        jwtBuilder.claim("id", "wss_car");
        jwtBuilder.claim("name", "wss_car");
        jwtBuilder.claim("value", "534534534111");


        // 定义私钥
        String HS256KEY = "534534534111";
        // 签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 计算签名key值
        Key signingKey = new SecretKeySpec(Base64.decodeBase64(HS256KEY), signatureAlgorithm.getJcaName());

        // 进行签名
        jwtBuilder.signWith(signatureAlgorithm, signingKey);

        // 获取token字符串
        String tokenString = jwtBuilder.compact();

        return tokenString;
    }

    public boolean isTokenValid(String token) {
        try {
            // 定义私钥
            String HS256KEY = "534534534111";
            // 签名算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            // 计算签名key值
            Key signingKey = new SecretKeySpec(Base64.decodeBase64(HS256KEY), signatureAlgorithm.getJcaName());

            Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token.trim()).getBody();
            Date date = claims.getExpiration();
            return new Date().before(date);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
