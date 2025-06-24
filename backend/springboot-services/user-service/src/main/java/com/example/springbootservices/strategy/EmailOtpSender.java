package com.example.springbootservices.strategy;

import org.springframework.stereotype.Component;

@Component
public class EmailOtpSender implements OtpSenderStrategy {

    @Override
    public void sendOtp(String email, String otp) {
        // Logic gửi email
        System.out.println("📧 Gửi OTP qua Email tới: " + email + ", mã OTP: " + otp);
    }

    @Override
    public String getChannel() {
        return "email";
    }
}

