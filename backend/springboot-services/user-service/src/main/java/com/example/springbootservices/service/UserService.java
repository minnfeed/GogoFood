package com.example.springbootservices.service;

import com.example.springbootservices.dto.RegisterRequest;
import com.example.springbootservices.model.entites.Role;
import com.example.springbootservices.model.entites.User;
import com.example.springbootservices.model.enums.Status;
import com.example.springbootservices.reponsitory.RoleRepository;
import com.example.springbootservices.reponsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findByName("CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(Status.INACTIVE);
        user.setRole(role);
        return userRepository.save(user);
    }
}

