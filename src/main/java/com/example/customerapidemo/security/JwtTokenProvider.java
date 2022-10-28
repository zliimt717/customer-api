package com.example.customerapidemo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {


    private String jwtSecret="spirngsecurity";

    public String generateToken(String userEmail){
        Instant now=Instant.now();
        Instant expiration=now.plus(10, ChronoUnit.MINUTES);
        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS256,jwtSecret)
                .compact();

    }

    public String generateToken(Authentication authentication){
        User user=(User) authentication.getPrincipal();
        return generateToken(user.getUsername());
    }

    public String getUserMailFromToken(String token){
        Claims claims=Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        // get Email information
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            log.error("Invalid JWT signature");
        }catch (MalformedJwtException ex){
            log.error("Invalid JWT token");
        }catch (ExpiredJwtException ex){
            log.error("Expired JWT token");
        }catch (UnsupportedJwtException ex){
            log.error("Unsupported JWT token");
        }catch (IllegalArgumentException ex){
            log.error("JWT claims string is empty");
        }
        return false;

    }

}
