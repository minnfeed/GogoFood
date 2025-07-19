package com.example.springbootservices.strategy;

import org.springframework.stereotype.Component;

@Component
public class TelegramOtpSender implements OtpSenderStrategy {

    @Override
    public void sendOtp(String chatId, String otp) {
        System.out.println("💬 Gửi OTP qua Telegram tới: " + chatId + ", mã OTP: " + otp);
    }

    @Override
    public String getChannel() {
        return "telegram";
    }
}
