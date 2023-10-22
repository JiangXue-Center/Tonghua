package com.hf.core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.security.DefaultAeadRequest;
import io.jsonwebtoken.impl.security.DefaultHashAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.security.Key;

public class JwtUtil {

    // Signature key
    public static final String SECRET = "eyJhbGciOiJIUzI1daxNiJ9";
    // Issuer
    public static final String ISSUER = "ArtnectHub";
    // Default subject (e.g., "guest")
    public static final String SUBJECT = "guest";
    // Token expiration time, 6 minutes in milliseconds
    public static final long TTLMILLIS = 30L * 24L * 60L * 60L * 1000L;


//    /**
//     * Generate a JWT token
//     *
//     * @param id Token ID
//     * @return Token string
//     */
//    public static String createJwtToken(String id) {
//        return createJwtToken(id, ISSUER, SUBJECT, TTLMILLIS);
//    }
//
//    public static String createJwtToken(String id, String subject) {
//        return createJwtToken(id, ISSUER, subject, TTLMILLIS);
//    }
//
//    public static String createJwtToken(String id, String issuer, String subject, long ttlMillis) {
//        return createJwtToken(id, issuer, subject, ttlMillis, null);
//    }
//
//    public static String createJwtToken(String id, String subject, Map<String, Object> paramMap) {
//        return createJwtToken(id, ISSUER, subject, TTLMILLIS, paramMap);
//    }
//
//    public static String createJwtToken(String id, String issuer, String subject, long ttlMillis, Map<String, Object> paramMap) {
//        // Signature algorithm
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        // Current time
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//
//        // Generate the key based on the secret
//        byte[] apiKeySecretBytes = Base64.getDecoder().decode(SECRET);
//        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//
//        // Token builder
//        JwtBuilder builder = Jwts.builder()
//                .setId(id)                // JWT ID
//                .setIssuedAt(now)         // Issued time
//                .setSubject(subject)      // Subject
//                .setIssuer(issuer)       // Issuer
//                .signWith(signingKey, signatureAlgorithm);
//        if (paramMap != null) {
//            builder.addClaims(paramMap);
//        }
//        // Set expiration time
//        if (ttlMillis >= 0) {
//            long expMillis = nowMillis + ttlMillis;
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp);
//        }
//        // Compact the token
//        return builder.compact();
//    }
//
//    // Parse a JWT token
//    public static Claims parseJwt(String jwt) {
//        Claims claims = null;
//        try {
//            claims = Jwts.parser()
//                    .setSigningKey(SECRET.getBytes()) // Set the secret key
//                    .parseClaimsJws(jwt)              // Parse the token
//                    .getBody();
//        } catch (ExpiredJwtException e) {
//            claims = e.getClaims();
//        }
//        return claims;
//    }
//
//    public static String getNewUUID() {
//        return UUID.randomUUID().toString().replaceAll("-", "");
//    }

    public static String createToken(String id) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(SUBJECT)
                .withClaim("id", id)
                .withExpiresAt(Date.from(new Date().toInstant().atZone(ZoneId.of("Asia/Shanghai")).toInstant().plusMillis(TTLMILLIS)))
                .sign(Algorithm.HMAC256(SECRET));
    }


    // 验证 JWT
    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .withSubject(SUBJECT)
                    .build()
                    .verify(token);
            return true; // 验证成功
        } catch (JWTVerificationException e) {
            return false; // 验证失败
        }
    }

    public static DecodedJWT getTokenInfo(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }



}
