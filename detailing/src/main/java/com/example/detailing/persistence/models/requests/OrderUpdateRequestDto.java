package com.example.detailing.persistence.models.requests;

import com.example.detailing.persistence.models.Users;
import lombok.Data;

@Data
public class OrderUpdateRequestDto {
    private String status;
    private Long userId;
}
