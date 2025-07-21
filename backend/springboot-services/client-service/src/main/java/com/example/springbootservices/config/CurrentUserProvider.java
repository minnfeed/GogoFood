package com.example.springbootservices.config;


import com.example.springbootservices.dto.UserDetailsImpl;
import com.example.springbootservices.exception.UnauthorizedException;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CurrentUserProvider {

    @Autowired
    private HttpServletRequest request;

    public UUID getCurrentUserId() {
        String userId = request.getHeader("X-User-Id");
        if (userId == null) throw new UnauthorizedException("Missing user ID");
        return UUID.fromString(userId);
    }

    public String getUserRole() {
        return request.getHeader("X-Role");
    }
}
