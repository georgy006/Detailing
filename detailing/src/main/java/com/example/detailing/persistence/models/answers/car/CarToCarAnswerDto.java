package com.example.detailing.persistence.models.answers.car;

import com.example.detailing.persistence.models.answers.order.ClientAnswerDto;
import lombok.Data;

@Data
public class CarToCarAnswerDto {
    private Long id;
    private String Brand;
    private String Model;
    private String stateNumber;
    private ClientAnswerDto clientAnswerDto;
}
