package com.example.apigateway.utils;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {

    // Chuỗi bí mật để ký JWT (nên để ở config)
    private final String jwtSecret = "A9sPz5t#Xb2*VqRwKm8LhTc@Ye1Fg6JuNz!BdC3EqWm7XyZh9TpVnKr@Me5Uj8QxCzLb6Nf7GdRxQv4TaYkHcWzXb9Uv2JgLp@WmTsZc7Qk";
    private final Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    // Giải mã và trích xuất thông tin người dùng từ token
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

    // Kiểm tra token có hợp lệ không
    public boolean validateToken(String token) {
        try {
            getClaimsFromToken(token); // sẽ ném lỗi nếu token sai
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