package com.pospayment.pospayment.util;


import com.pospayment.pospayment.exception.TokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Slf4j
public class JwtToken {

    @Value("${jwt.secret}")
    private String SECRET;

    public static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public String createToken(String username) {
        log.info("Creating token for user : {}", username);
        return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public String getUsername(String token) {
        log.info("Getting username from token : {}", token);
       try {
           return Jwts.parser().setSigningKey(SECRET).build().parseSignedClaims(token).getPayload().getSubject();
       } catch (Exception e) {
           throw new TokenException("Invalid token", e);
       }
    }

    public boolean validateToken(String token) throws TokenException {
        log.info("Validating token : {}", token);
        try {
            Jwts.parser().setSigningKey(SECRET).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            throw new TokenException("Invalid token", e);
        }
    }
}