package com.example.springbootservices.config;

import com.google.common.net.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ExternalWebClientConfig {

    @Bean(name = "osmWebClient")
    public WebClient osmWebClient() {
        return WebClient.builder()
                .baseUrl("https://nominatim.openstreetmap.org")
                .defaultHeader(HttpHeaders.USER_AGENT, "GogoFood/1.0 (https://gogofood.vn)")
                .build();
    }
}