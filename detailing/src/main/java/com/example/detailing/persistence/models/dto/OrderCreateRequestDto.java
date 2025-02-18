package com.example.detailing.persistence.models.dto;

import lombok.Data;

@Data
public class OrderCreateRequestDto {

    private String status;
    private Long carId;
    private Long serviceId;
    private Long userId;

}
