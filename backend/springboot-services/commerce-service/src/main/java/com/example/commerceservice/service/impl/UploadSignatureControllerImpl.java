package com.example.commerceservice.service.impl;

import com.example.commerceservice.config.CloudinaryConfig;
import com.example.commerceservice.service.UploadSignatureController;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadSignatureControllerImpl implements UploadSignatureController {
    private final CloudinaryConfig cloudinaryConfig;

    @GetMapping("/signature")
    public Map<String, Object> getUploadSignature() {
        long timestamp = System.currentTimeMillis() / 1000;

        String stringToSign = "timestamp=" + timestamp + cloudinaryConfig.getApiSecret();
        String signature = DigestUtils.sha1Hex(stringToSign);

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", timestamp);
        response.put("signature", signature);
        response.put("apiKey", cloudinaryConfig.getApiKey());
        response.put("cloudName", cloudinaryConfig.getCloudName());

        return response;
    }
}
