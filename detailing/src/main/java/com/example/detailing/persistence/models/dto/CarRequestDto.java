package com.example.detailing.persistence.models.dto;

import lombok.Data;


@Data
public class CarRequestDto {
    private String brand;
    private String model;
    private String stateNumber;
}
