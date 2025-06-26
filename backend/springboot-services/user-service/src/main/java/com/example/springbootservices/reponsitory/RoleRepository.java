package com.example.springbootservices.reponsitory;

import com.example.springbootservices.model.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    // Tìm vai trò theo tên
    Optional<Role> findByName(String name);

    // Kiểm tra vai trò đã tồn tại chưa
    boolean existsByName(String name);
}
