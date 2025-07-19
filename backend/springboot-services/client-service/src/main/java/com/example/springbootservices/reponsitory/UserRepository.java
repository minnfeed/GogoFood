package com.example.springbootservices.reponsitory;

import com.example.springbootservices.model.entites.User;
import com.example.springbootservices.model.projection.CustomerAdminView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query("""
        SELECT u.id AS id,
               u.username AS username,
               u.email AS email,
               u.fullName AS fullName,
               u.phone AS phone,
               u.status AS status,
               u.createdAt AS createdAt,
               r.name AS roleName
        FROM User u
        JOIN u.role r
        WHERE r.name = 'CUSTOMER'
    """)
    Page<CustomerAdminView> findAllCustomersForAdmin(Pageable pageable);
}