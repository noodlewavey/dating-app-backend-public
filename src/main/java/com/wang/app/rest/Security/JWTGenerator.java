package com.wang.app.rest.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.security.Key;

import java.util.Date;

//this class generates a token for us...
@Component
public class JWTGenerator {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        return token;
        //we need other methods...we want to get username from jwt and to validate token


    }

    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
            //this is a valid token
            //updated deprecated code
        }catch(Exception e){
            System.out.println(token);
            System.out.println("Invalid JWT token, expired or incorrect");
            //form of authentication
            //my code is entering this exception....
        }
        return false;
    }
}
