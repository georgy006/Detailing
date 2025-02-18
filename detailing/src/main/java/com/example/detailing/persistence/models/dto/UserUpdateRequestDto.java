package com.example.detailing.persistence.models.dto;

import lombok.Data;

@Data
public class UserUpdateRequestDto {
    private String name;
    private String email;
}
