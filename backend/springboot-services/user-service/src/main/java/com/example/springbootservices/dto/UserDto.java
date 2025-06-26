package com.example.springbootservices.dto;

import com.example.springbootservices.model.enums.Gender;
import com.example.springbootservices.model.enums.Status;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String username;
    private String email;
    private String fullName;
    private String phone;
    private Status status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String role;
    private String avatarUrl;
    private LocalDate dateOfBirth;
    private Gender gender;
    private List<AddressDto> addresses;

}
