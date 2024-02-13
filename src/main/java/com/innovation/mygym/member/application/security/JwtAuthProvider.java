package com.innovation.mygym.member.application.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtAuthProvider {

    private final String secretKey;
    private final int accessTokenExpire;
    private final int refreshTokenExpire;

    public JwtAuthProvider(@Value("${jwt.secret-key}") String secretKey) {
        this.secretKey = secretKey;
        this.accessTokenExpire = 1000 * 60 * 30;
        this.refreshTokenExpire = 1000 * 60 * 60 * 24 * 14;
    }

    public String generateAccessToken(Authentication authentication) {

        AccountContext accountContext = (AccountContext) authentication.getPrincipal();

        Claims claims = Jwts.claims();
        claims.put("username", accountContext.getUsername());
        claims.put("userId", accountContext.getMemberId());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpire))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generateRefreshToken() {

        Claims claims = Jwts.claims();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpire))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public static String getUsername(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("username", String.class);
    }

    public Long getUserId(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("userId", Long.class);
    }
}
