package com.example.springbootservices.model.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CustomerAdminView {
    UUID getId();
    String getUsername();
    String getEmail();
    String getFullName();
    String getPhone();
    String getStatus();
    LocalDateTime getCreatedAt();
    String getRoleName();
}