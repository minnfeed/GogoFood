package com.example.springbootservices.factory;

import com.example.springbootservices.strategy.OtpSenderStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OtpSenderFactory {

    private final Map<String, OtpSenderStrategy> strategyMap = new HashMap<>();

    @Autowired
    public OtpSenderFactory(List<OtpSenderStrategy> strategies) {
        for (OtpSenderStrategy strategy : strategies) {
            strategyMap.put(strategy.getChannel(), strategy);
        }
    }

    public OtpSenderStrategy getSender(String channel) {
        OtpSenderStrategy strategy = strategyMap.get(channel.toLowerCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Không tìm thấy kênh gửi OTP phù hợp: " + channel);
        }
        return strategy;
    }
}
