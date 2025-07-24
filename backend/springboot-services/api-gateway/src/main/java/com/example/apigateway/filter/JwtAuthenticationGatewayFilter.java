package com.example.apigateway.filter;

import com.example.apigateway.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Component
public class JwtAuthenticationGatewayFilter implements GlobalFilter {

    @Autowired
    private JwtUtils jwtUtils;

    private static final List<String> PUBLIC_PATHS = List.of(
            "/auth/login", "/auth/register", "/swagger-ui", "/v3/api-docs", "/error"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = extractToken(exchange.getRequest());
        String path = exchange.getRequest().getPath().value();

        if (isPublicPath(path)) return chain.filter(exchange);

        if (token != null && jwtUtils.validateToken(token)) {
            UUID userId = jwtUtils.getUserIdFromToken(token);
            String userName = jwtUtils.getUsernameFromToken(token);
            String role = jwtUtils.getRoleFromToken(token);

            // In ra log để chắc chắn header được thêm
            System.out.println("🔐 Injecting Headers -> userId: " + userId + ", userName: " + userName + ", role: " + role);

            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("Authorization", "Bearer " + token)
                    .header("X-User-Id", userId.toString())
                    .header("X-User-Name", userName)
                    .header("X-Role", role)
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        }

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    private String extractToken(ServerHttpRequest request) {
        List<String> authHeaders = request.getHeaders().getOrEmpty("Authorization");
        if (!authHeaders.isEmpty() && authHeaders.get(0).startsWith("Bearer ")) {
            return authHeaders.get(0).substring(7);
        }
        return null;
    }

    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }

}


