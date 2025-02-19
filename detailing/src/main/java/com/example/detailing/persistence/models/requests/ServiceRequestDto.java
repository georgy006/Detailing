package com.example.detailing.persistence.models.requests;

import lombok.Data;

@Data
public class ServiceRequestDto {
    private String name;
    private Double price;
    private String password;
    private Integer time; // Время в минутах
    private String description;
}
