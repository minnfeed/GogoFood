package com.example.springbootservices.controller;

import com.example.springbootservices.model.entites.User;
import com.example.springbootservices.model.enums.Status;
import com.example.springbootservices.service.OtpService;
import com.example.springbootservices.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Tag(name = "OTP", description = "Gửi và xác minh mã OTP qua email hoặc phương thức khác")
@RestController
@RequestMapping("/api/otp")
public class OtpController {

    private final OtpService otpService;

    @Autowired
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }
    @Autowired
    UserService userService;

    @Operation(
            summary = "Gửi mã OTP",
            description = """
            Gửi mã OTP đến người dùng qua phương thức được chọn (ví dụ: email).
            Dùng trong quá trình xác thực tài khoản, đặt lại mật khẩu,...
            """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "OTP đã được gửi thành công",
                            content = @Content(schema = @Schema(example = "{ \"message\": \"OTP đã được gửi qua email\" }"))),
                    @ApiResponse(responseCode = "400", description = "Tham số không hợp lệ",
                            content = @Content(schema = @Schema(implementation = String.class)))
            }
    )
    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendOtp(
            @RequestParam String channel,
            @RequestParam String to) {
        otpService.sendOtp(channel, to);
        Map<String, String> response = new HashMap<>();
        response.put("message", "OTP đã được gửi qua " + channel);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Xác minh mã OTP",
            description = """
            Kiểm tra mã OTP được gửi tới người dùng.
            Nếu đúng, tiến hành kích hoạt tài khoản người dùng (nếu có).
            """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "OTP hợp lệ",
                            content = @Content(schema = @Schema(example = "{ \"message\": \"Xác minh OTP thành công\" }"))),
                    @ApiResponse(responseCode = "400", description = "OTP không đúng hoặc đã hết hạn",
                            content = @Content(schema = @Schema(example = "{ \"message\": \"OTP không đúng hoặc đã hết hạn\" }")))
            }
    )
    @PostMapping("/verify")
    public ResponseEntity<Map<String, String>> verifyOtp(
            @RequestParam String to,
            @RequestParam String otp) {
        boolean isValid = otpService.verifyOtp(to, otp);
        Map<String, String> response = new HashMap<>();
        if (isValid) {
            response.put("message", "Xác minh OTP thành công");
            System.out.println("Active"+userService.activateUserByEmail(to));
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "OTP không đúng hoặc đã hết hạn");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
