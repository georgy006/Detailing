package com.example.detailing.persistence.models.answers;

import lombok.Data;

@Data
public class ServiceAnswerDto {
    private String nameService;
    private Double Price;
    private Integer Time;
    private String description;
}
