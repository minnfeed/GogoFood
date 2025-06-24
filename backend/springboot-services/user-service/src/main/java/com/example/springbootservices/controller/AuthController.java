package com.example.springbootservices.controller;

import com.example.springbootservices.dto.*;
import com.example.springbootservices.service.UserService;
import com.example.springbootservices.utils.JwtUtil;
import jakarta.validation.Valid;
import com.example.springbootservices.dto.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springbootservices.model.entites.User;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        User user = userService.getUserByUsername(userDetails.getUsername());

        String token = jwtUtil.generateToken(userDetails);

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

        LoginResponse response = new LoginResponse(
                token,
                jwtUtil.getJwtExpirationMs(),
                "Bearer",
                dto
        );

        return ResponseEntity.ok(response);
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            User user = userService.register(request);
            return ResponseEntity.ok("User registered successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
