package com.wang.app.rest.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

//this class generates a token for us...
@Component
public class JWTGenerator {
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes()))
                .compact();
        return token;
        //we need other methods...we want to get username from jwt and to validate token


    }

    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes()))
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
