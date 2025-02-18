package com.example.detailing.persistence.models.dto;

import lombok.Data;

@Data
public class UserCreateRequestDto {
    private String name;
    private String email;
    private String password;
    private String role;
}
