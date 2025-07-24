package com.example.springbootservices.controller;

import com.example.springbootservices.dto.LoginResponse;

import com.example.springbootservices.service.OAuth2LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Tag(name = "OAuth2 Authentication", description = "Đăng nhập thông qua Google OAuth2")
@Component
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final OAuth2LoginService oAuth2LoginService;
    private final ObjectMapper objectMapper;

    @Operation(
            summary = "Login bằng Google OAuth2",
            description = """
            Đăng nhập thông qua Google OAuth2. Sau khi xác thực, người dùng sẽ được chuyển hướng đến `CustomOAuth2SuccessHandler`, 
            nơi xử lý token và trả về JWT cùng thông tin người dùng.
            
            ✅ URL khởi đầu thường là `/oauth2/authorization/google`
            ⚠️ Đây là luồng redirect, không gọi bằng Postman hay Swagger UI.
            """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirect tới Google OAuth2"),
            @ApiResponse(responseCode = "200", description = "Đăng nhập thành công và nhận về JWT token từ backend")
    })
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        LoginResponse loginResponse = oAuth2LoginService.processOAuth2Login(oAuth2User);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(loginResponse));
    }
}
