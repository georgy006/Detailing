package com.example.detailing.persistence.models.requests;

import lombok.Data;

@Data
public class OrderCreateRequestDto {

    private String status;
    private Long carId;
    private Long serviceId;
    private Long userId;

}
