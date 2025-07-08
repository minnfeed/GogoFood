package com.example.springbootservices.controller;

import com.example.springbootservices.model.entites.User;
import com.example.springbootservices.model.enums.Status;
import com.example.springbootservices.service.OtpService;
import com.example.springbootservices.service.UserService;
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

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendOtp(
            @RequestParam String channel,
            @RequestParam String to) {
        otpService.sendOtp(channel, to);
        Map<String, String> response = new HashMap<>();
        response.put("message", "OTP đã được gửi qua " + channel);
        return ResponseEntity.ok(response);
    }

    // Xác minh OTP
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
