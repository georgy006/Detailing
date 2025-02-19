package com.example.detailing.persistence.models.answers.order;

import lombok.Data;

@Data
public class ServiceAnswerDto {
    private String nameService;
    private Double Price;
    private Integer Time;
    private String description;
}
