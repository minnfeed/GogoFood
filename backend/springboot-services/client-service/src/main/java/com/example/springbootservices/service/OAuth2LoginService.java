package com.example.springbootservices.service;

import com.example.springbootservices.dto.LoginResponse;
import com.example.springbootservices.dto.UserDto;
import com.example.springbootservices.model.entites.Role;
import com.example.springbootservices.model.entites.User;
import com.example.springbootservices.model.enums.Status;
import com.example.springbootservices.reponsitory.RoleRepository;
import com.example.springbootservices.reponsitory.UserRepository;
import com.example.springbootservices.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OAuth2LoginService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public LoginResponse processOAuth2Login(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String picture = oAuth2User.getAttribute("picture");

        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user;

        if (optionalUser.isEmpty()) {
            Role role = roleRepository.findByName("CUSTOMER")
                    .orElseThrow(() -> new RuntimeException("Role not found"));

            user = new User();
            user.setEmail(email);
            user.setFullName(name);
            user.setAvatarUrl(picture);
            user.setUsername(email);
            user.setPassword(UUID.randomUUID().toString()); // random password
            user.setRole(role);
            user.setStatus(Status.INACTIVE);
            user = userRepository.save(user);
        } else {
            user = optionalUser.get();
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setPhone(user.getPhone());
        dto.setStatus(user.getStatus());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setRole(user.getRole().getName());

        return new LoginResponse(
                token,
                dto
        );
    }
}
