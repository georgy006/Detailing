package com.example.detailing.persistence.models.answers.order;

import lombok.Data;

@Data
public class OrderAnswerDto {
    private Long id;
    private String Status;

    private CarAnswerDto carAnswerDto;

    //Клиент
    private ClientAnswerDto clientAnswerDto;

    //Услуга
    ServiceAnswerDto serviceAnswerDto;

    //Staff
    private StaffAnswerDto staffAnswerDto;
}
