package com.example.springbootservices.strategy;

public interface OtpSenderStrategy {
    void sendOtp(String recipient, String otp);
    String getChannel(); // ví dụ: "email", "telegram", "sms"
}
