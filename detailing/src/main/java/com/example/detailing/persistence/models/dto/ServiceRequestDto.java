package com.example.detailing.persistence.models.dto;

import lombok.Data;

@Data
public class ServiceRequestDto {
    private String name;
    private Double price;
    private String password;
    private Integer time; // Время в минутах
    private String description;
}
