package com.example.springbootservices.controller;

import com.example.springbootservices.config.CurrentUserProvider;
import com.example.springbootservices.dto.*;
import com.example.springbootservices.model.entites.User;
import com.example.springbootservices.model.enums.Status;
import com.example.springbootservices.service.OtpService;
import com.example.springbootservices.service.UserService;
import com.example.springbootservices.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Tag(name = "User Account", description = "Các API liên quan đến tài khoản người dùng")
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

    @Autowired
    CurrentUserProvider currentUserProvider;

    @Operation(summary = "Đăng nhập", description = "Xác thực người dùng và trả về JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Đăng nhập thành công"),
            @ApiResponse(responseCode = "401", description = "Sai tài khoản hoặc mật khẩu")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        User user = userService.getUserByUsername(userDetails.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        UserDto userDto = userService.convertToDto(user);
        LoginResponse response = new LoginResponse(
                token,
                userDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @Operation(summary = "Register new user", description = "Creates a new user account with the provided email, username and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully!"),
            @ApiResponse(responseCode = "400", description = "Invalid input or user already exists", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            request.setPassword(passwordEncoder.encode(request.getPassword()));
            User user = userService.register(request);
            return ResponseEntity.ok("User registered successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @Operation(summary = "Lấy thông tin người dùng hiện tại", description = "Trả về thông tin chi tiết của người dùng đang đăng nhập")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lấy thông tin thành công"),
            @ApiResponse(responseCode = "401", description = "Chưa đăng nhập hoặc token không hợp lệ")
    })
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {

        User user = userService.getUserByID(currentUserProvider.getCurrentUserId());
        UserDto userDto = userService.convertToDto(user);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Cập nhật thông tin cá nhân", description = "Cập nhật thông tin như họ tên, số điện thoại, ngày sinh,...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cập nhật thành công"),
            @ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ"),
    })

    @PutMapping("/me")
    public ResponseEntity<?> updateCurrentUser(
            @RequestBody UpdateUserRequest request) {
        User user = userService.getUserByID(currentUserProvider.getCurrentUserId());

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

    @Operation(summary = "Đổi mật khẩu", description = "Yêu cầu cung cấp mật khẩu cũ và mật khẩu mới")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Đổi mật khẩu thành công"),
            @ApiResponse(responseCode = "400", description = "Mật khẩu cũ không chính xác")
    })

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request) {
        User user = userService.getUserByID(currentUserProvider.getCurrentUserId());

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Mật khẩu cũ không đúng");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userService.save(user);

        return ResponseEntity.ok("Đổi mật khẩu thành công");
    }

    @Operation(summary = "Quên mật khẩu", description = "Gửi mã OTP qua email hoặc phương thức được chọn để đặt lại mật khẩu")
    @Parameters({
            @Parameter(name = "channel", description = "Phương thức gửi OTP, ví dụ: email", example = "email"),
            @Parameter(name = "to", description = "Địa chỉ nhận OTP", example = "user@example.com")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gửi OTP thành công"),
            @ApiResponse(responseCode = "404", description = "Email không tồn tại")
    })
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

    @Operation(summary = "Đặt lại mật khẩu", description = "Xác thực OTP và đặt lại mật khẩu mới")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Đặt lại mật khẩu thành công"),
            @ApiResponse(responseCode = "400", description = "OTP không hợp lệ hoặc hết hạn"),
            @ApiResponse(responseCode = "404", description = "Email không tồn tại")
    })
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

    @Operation(summary = "Xoá tài khoản", description = "Xoá mềm tài khoản người dùng hiện tại")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Xoá tài khoản thành công"),
            @ApiResponse(responseCode = "401", description = "Không được xác thực")
    })
    @PutMapping("/me/delete")
    public ResponseEntity<?> deleteCurrentUser() {
        User user = userService.getUserByID(currentUserProvider.getCurrentUserId());
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        user.setStatus(Status.DELETED);
        userService.save(user);
        UserDto userDto = userService.convertToDto(user);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Upload ảnh đại diện", description = "Tải lên ảnh đại diện mới cho người dùng")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Upload thành công"),
            @ApiResponse(responseCode = "500", description = "Lỗi khi upload ảnh")
    })

    @PutMapping("/me/upload-avatar")
    public ResponseEntity<?> uploadAvatar( @RequestParam("avatar") MultipartFile file){
        User user = userService.getUserByID(currentUserProvider.getCurrentUserId());
        try{
            userService.uploadAvatar(file,user);
            return ResponseEntity.ok("Upload thành công");
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi upload ảnh: " + e.getMessage());
        }
    }
}
