package com.example.detailing.persistence.models.requests;

import lombok.Data;

@Data
public class UserCreateRequestDto {
    private String name;
    private String email;
    private String password;
    private String role;
}
