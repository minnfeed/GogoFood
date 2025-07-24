package com.example.apigateway.utils;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {

    private final Key key;

    @Autowired
    public JwtUtils(@Value("${jwt.secret}") String jwtSecret) {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public UUID getUserIdFromToken(String token) {
        String userId = getClaimsFromToken(token).get("userId", String.class);
        return UUID.fromString(userId);
    }

    public String getRoleFromToken(String token) {
        return getClaimsFromToken(token).get("role", String.class);
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            getClaimsFromToken(token);
            return true;
        } catch (ExpiredJwtException ex) {
            System.err.println("JWT expired: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            System.err.println("JWT unsupported: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            System.err.println("JWT malformed: " + ex.getMessage());
        } catch (SignatureException ex) {
            System.err.println("JWT signature invalid: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("JWT invalid: " + ex.getMessage());
        }
        return false;
    }
}
