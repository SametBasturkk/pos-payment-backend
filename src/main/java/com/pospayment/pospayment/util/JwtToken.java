package com.pospayment.pospayment.util;


import com.pospayment.pospayment.exception.TokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtToken {

    @Value("${jwt.secret}")
    private String SECRET;

    public static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public String createToken(String username) {
        return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean validateToken(String token) throws TokenException {
        try {
            Jwts.parser().setSigningKey(SECRET).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            throw new TokenException("Invalid token", e);
        }
    }
}