package com.example.springbootservices.controller;

import com.example.springbootservices.dto.*;
import com.example.springbootservices.service.OtpService;
import com.example.springbootservices.service.UserService;
import com.example.springbootservices.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.springbootservices.model.entites.User;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    OtpService otpService;

    @Operation(summary = "Đăng nhập người dùng", description = "Đăng nhập và trả về JWT token")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        User user = userService.getUserByUsername(userDetails.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        UserDto userDto = userService.convertToDto(user);
        LoginResponse response = new LoginResponse(
                token,
                jwtUtil.getJwtExpirationMs(),
                "Bearer",
                userDto
        );
        return ResponseEntity.ok(response);
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            request.setPassword(passwordEncoder.encode(request.getPassword()));
            User user = userService.register(request);
            return ResponseEntity.ok("User registered successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {

        User user = userService.getUserByUsername(userDetails.getUsername());

        UserDto userDto = userService.convertToDto(user);
        return ResponseEntity.ok(userDto);
    }
    @PutMapping("/me")
    public ResponseEntity<?> updateCurrentUser(@AuthenticationPrincipal UserDetails userDetails,
                                               @RequestBody UpdateUserRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());

        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setAvatarUrl(request.getAvatarUrl());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        userService.save(user);
        UserDto userDto = userService.convertToDto(user);
        return ResponseEntity.ok(userDto);
    }
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@AuthenticationPrincipal UserDetails userDetails,
                                            @RequestBody ChangePasswordRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Mật khẩu cũ không đúng");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userService.save(user);

        return ResponseEntity.ok("Đổi mật khẩu thành công");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String channel,
                                            @RequestParam String to) {
        Map<String, String> response = new HashMap<>();
        Optional<User> optionalUser = userService.findByEmail(to);
        if (optionalUser.isEmpty()) {
            response.put("message", "Email không tồn tại trong hệ thống");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        otpService.sendOtp(channel, to);
        response.put("message", "OTP đã được gửi qua " + channel);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody ResetPasswordRequest request) {
        Map<String, String> response = new HashMap<>();

        // 1. Kiểm tra OTP
        boolean validOtp = otpService.verifyOtp(request.getEmail(), request.getOtp());
        if (!validOtp) {
            response.put("message", "OTP không hợp lệ hoặc đã hết hạn");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // 2. Tìm user
        Optional<User> optionalUser = userService.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            response.put("message", "Email không tồn tại");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        User user = optionalUser.get();

        // 3. Mã hóa mật khẩu mới
        String hashedPassword = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(hashedPassword);
        userService.save(user); // hoặc userRepository.save(user);

        response.put("message", "Đặt lại mật khẩu thành công");
        return ResponseEntity.ok(response);
    }
    @PutMapping("/me/delete")
    public ResponseEntity<?> deleteCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        // Lấy user từ username
        User user = userService.getUserByUsername(userDetails.getUsername());
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        // Lưu lại
        userService.save(user);
        // Trả về thông tin user đã bị xoá mềm (nếu cần)
        UserDto userDto = userService.convertToDto(user);
        return ResponseEntity.ok(userDto);
    }

}
