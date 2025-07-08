package com.example.springbootservices.service;

import com.example.springbootservices.dto.AddressDto;
import com.example.springbootservices.dto.RegisterRequest;
import com.example.springbootservices.dto.UserDto;
import com.example.springbootservices.model.entites.Address;
import com.example.springbootservices.model.entites.Role;
import com.example.springbootservices.model.entites.User;
import com.example.springbootservices.model.enums.Status;
import com.example.springbootservices.reponsitory.RoleRepository;
import com.example.springbootservices.reponsitory.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


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
        user.setPassword(request.getPassword());
        user.setStatus(Status.INACTIVE);
        user.setRole(role);
        return userRepository.save(user);
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public Boolean activateUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user"));

        user.setStatus(Status.ACTIVE);
        save(user);
        return true;
    }
    public void save(User user) {
        userRepository.save(user);
    }
    @Transactional
    public UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setPhone(user.getPhone());
        dto.setStatus(user.getStatus());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setRole(user.getRole().getName());
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setGender(user.getGender());

        List<AddressDto> addressDtos = user.getAddresses().stream()
                .map(address -> new AddressDto(
                        address.getId(),
                        address.getStreet(),
                        address.getWard(),
                        address.getDistrict(),
                        address.getCity(),
                        address.isDefault()
                ))
                .collect(Collectors.toList());

        dto.setAddresses(addressDtos);
        return dto;
    }
    public Optional<User> findByEmail(String mail) {
        return Optional.ofNullable(userRepository.findByEmail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user")));
    }
}

