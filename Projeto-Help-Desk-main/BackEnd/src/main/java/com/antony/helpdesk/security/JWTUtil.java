package com.antony.helpdesk.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt.expiration}")
    private Long expirationTime;

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(String email){
        Key hmacKey = new SecretKeySpec(secretKey.getBytes(),SignatureAlgorithm.HS512.getJcaName());

        String jwtToken = Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(hmacKey)
                .compact();

        return jwtToken;
    }

    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);
        if(claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if(username != null && expirationDate != null && now.before(expirationDate)){
                return true;
            }
        }
        return false;
    }

    private Claims getClaims(String token) {
        try{
            return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
        }catch (Exception e){
            return null;
        }
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if(claims != null) {
            return claims.getSubject();
        }
        return null;
    }

}
