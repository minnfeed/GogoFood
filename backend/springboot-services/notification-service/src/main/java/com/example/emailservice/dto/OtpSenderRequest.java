package com.example.emailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OtpSenderRequest {
    private String to;
    private String subject;
    private String otp;

}
