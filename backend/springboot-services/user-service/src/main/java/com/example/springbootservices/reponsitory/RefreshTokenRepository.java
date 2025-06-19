package com.example.springbootservices.reponsitory;

import com.example.springbootservices.model.entites.RefreshToken;
import com.example.springbootservices.model.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    // Tìm theo token string
    Optional<RefreshToken> findByToken(String token);

    // Xóa refresh token theo user
    int deleteByUser(User user);
}
