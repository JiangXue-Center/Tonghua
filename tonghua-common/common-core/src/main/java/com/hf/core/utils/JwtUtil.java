package com.hf.core.utils;

import io.jsonwebtoken.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {

    //签名的密钥
    public static final String SECRET = "eyJhbGciOiJIUzI1daxNiJ9";
    //签发者
    public static final String ISSUER = "Hf";
    //面向的用户，默认游客
    public static final String SUBJECT = "guest";
    //签发时间，6分钟
    public static final Integer TTLMILLIS= 360000;


    /**
     * 生成token
     *
     * @param id 编号
     * @return
     */
    public static String createJwtToken(String id){
        return createJwtToken(id,ISSUER,SUBJECT,TTLMILLIS);
    }

    public static String createJwtToken(String id,String subject){
        return createJwtToken(id,ISSUER,subject,TTLMILLIS);
    }

    /**
     * 生成Token
     *
     * @param id        编号
     * @param issuer    该JWT的签发者，是否使用是可选的
     * @param subject   该JWT所面向的用户，是否使用是可选的；
     * @param ttlMillis 签发时间 （有效时间，过期会报错）
     * @return token String
     */
    public static String createJwtToken(String id, String issuer, String subject, long ttlMillis) {
        return createJwtToken(id,issuer,subject,ttlMillis,null);
    }

    /**
     * 生成Token
     *
     * @param id        编号
     * @param subject   该JWT所面向的用户，是否使用是可选的；
     * @param paramMap  自定义参数
     * @return token String
     */
    public static String createJwtToken(String id, String subject, Map<String, Object> paramMap) {
        return createJwtToken(id,ISSUER,subject,TTLMILLIS,paramMap);
    }

    /**
     * 生成Token
     *
     * @param id        编号
     * @param subject   该JWT所面向的用户，是否使用是可选的；
     * @param ttlMillis 签发时间 （有效时间，过期会报错）
     * @param paramMap  自定义参数
     * @return token String
     */
    public static String createJwtToken(String id, String issuer, String subject, long ttlMillis, Map<String, Object> paramMap) {

        //签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //签发当前时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //根据密钥生成密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());

        //Token建造器
        JwtBuilder builder = Jwts
                .builder()
                .setId(id)//JWT ID用于标识该JWT
                .setIssuedAt(now)//发布时间
                .setSubject(subject)//主题
                .setIssuer(issuer)//发行人
                .signWith(signatureAlgorithm,signingKey);
        if (paramMap != null){
            builder.addClaims(paramMap);//自定义参数
        }
        //设置过期时间
        if(ttlMillis>=0){
            long expMillis = nowMillis + ttlMillis;
            Date exp =new Date(expMillis);
            builder.setExpiration(exp);//到期时间
        }

        //压缩
        return builder.compact();
    }


    //解析Token
    public static Claims parseJwt(String jwt){
//        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).parseClaimsJws(jwt).getBody();
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET) // 设置标识名
                    .parseClaimsJws(jwt)  //解析token
                    .getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }

    public static String getNewUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}