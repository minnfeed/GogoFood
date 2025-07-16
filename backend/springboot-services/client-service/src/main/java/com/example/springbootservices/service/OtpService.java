package com.example.springbootservices.service;

import com.example.springbootservices.factory.OtpSenderFactory;
import com.example.springbootservices.strategy.OtpSenderStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;

import java.time.Duration;

@Service
public class OtpService {

    private final OtpSenderFactory otpSenderFactory;
    private final StringRedisTemplate redisTemplate;
    private static final Duration OTP_TTL = Duration.ofMinutes(5);
    private static final String OTP_PREFIX = "otp:";
    SecureRandom secureRandom = new SecureRandom();

    @Autowired
    public OtpService(OtpSenderFactory otpSenderFactory, StringRedisTemplate redisTemplate) {
        this.otpSenderFactory = otpSenderFactory;
        this.redisTemplate = redisTemplate;
    }

    public void sendOtp(String channel, String to) {
        String otpCode = generateOtpCode();

        // Gửi OTP qua channel tương ứng
        OtpSenderStrategy sender = otpSenderFactory.getSender(channel);
        sender.sendOtp(to, otpCode);

        // Lưu OTP vào Redis với TTL
        redisTemplate.opsForValue().set(OTP_PREFIX + to, otpCode, OTP_TTL);
    }

    // Xác thực OTP
    public boolean verifyOtp(String to, String inputOtp) {
        String key = OTP_PREFIX + to;
        String storedOtp = redisTemplate.opsForValue().get(key);
        if (inputOtp.equals(storedOtp)) {
            redisTemplate.delete(key); // xoá OTP sau khi dùng
            return true;
        }
        return false;
    }

    // Tạo mã OTP ngẫu nhiên
    private String generateOtpCode() {
        int otp = secureRandom.nextInt(1000000);
        return String.valueOf(otp);
    }
}

