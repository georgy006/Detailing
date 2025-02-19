package com.example.detailing.persistence.models.requests;

import lombok.Data;


@Data
public class CarRequestDto {
    private String brand;
    private String model;
    private String stateNumber;
}
