package com.example.detailing.persistence.models.requests;

import lombok.Data;

@Data
public class UserUpdateRequestDto {
    private String name;
    private String email;
}
