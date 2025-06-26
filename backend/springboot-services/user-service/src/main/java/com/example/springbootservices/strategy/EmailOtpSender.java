package com.example.springbootservices.strategy;

import com.example.springbootservices.client.NotificationClient;
import com.example.springbootservices.dto.OtpSenderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class EmailOtpSender implements OtpSenderStrategy {
    private final NotificationClient notificationClient;
    @Override
    public void sendOtp(String email, String otp) {
        OtpSenderRequest request = new OtpSenderRequest(email, otp);

        try {
            ResponseEntity<String> response = notificationClient.sendOtp(request);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("✅ Gửi OTP thành công tới email: " + email);
            } else {
                System.err.println("❌ Gửi OTP thất bại. Mã lỗi: " + response.getStatusCode());
            }

        } catch (Exception e) {
            System.err.println("🚨 Lỗi khi gửi OTP tới email: " + email);
            e.printStackTrace(); // In stack trace để debug
        }
    }

    @Override
    public String getChannel() {
        return "email";
    }
}

