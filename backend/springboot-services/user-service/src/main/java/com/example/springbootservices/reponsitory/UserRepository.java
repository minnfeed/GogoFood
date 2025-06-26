package com.example.springbootservices.reponsitory;

import com.example.springbootservices.model.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    // Tìm user theo username
    Optional<User> findByUsername(String username);

    // Tìm danh sách user theo status (ACTIVE, BANNED, INACTIVE)
    List<User> findByStatus(String status);

    // Kiểm tra username đã tồn tại chưa
    boolean existsByUsername(String username);

    // Kiểm tra email đã tồn tại chưa
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

}