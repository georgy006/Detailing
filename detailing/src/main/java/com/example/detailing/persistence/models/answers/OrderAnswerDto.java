package com.example.detailing.persistence.models.answers;

import lombok.Data;

@Data
public class OrderAnswerDto {
    private Long id;
    private String Status;

    private CarAnswerDto carAnswerDto;

    private ClientAnswerDto clientAnswerDto;

    private ServiceAnswerDto serviceAnswerDto;

    private StaffAnswerDto staffAnswerDto;
}
