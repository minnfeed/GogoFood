package com.example.springbootservices.model.entites;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(name = "expiry_date", nullable = false)
    private Timestamp expiryDate;

    // Getters & Setters
}
