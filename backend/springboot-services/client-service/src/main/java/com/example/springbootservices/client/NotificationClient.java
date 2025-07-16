package com.example.springbootservices.client;

import com.example.springbootservices.dto.OtpSenderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service") // tên chính là spring.application.name
public interface NotificationClient {

    @PostMapping("/api/email/send")
    ResponseEntity<String> sendOtp(@RequestBody OtpSenderRequest request);
}

