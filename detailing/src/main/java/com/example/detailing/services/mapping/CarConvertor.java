package com.example.detailing.services.mapping;

import com.example.detailing.persistence.models.Car;
import com.example.detailing.persistence.models.Users;
import com.example.detailing.persistence.models.answers.CarToCarAnswerDto;
import com.example.detailing.persistence.models.answers.ClientAnswerDto;
import com.example.detailing.persistence.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarConvertor {

    @Autowired
    UsersRepository usersRepository;

    public CarToCarAnswerDto convertCarToCarAnswerDto(Car car){
        Users user = usersRepository.findById(car.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        CarToCarAnswerDto carDto = new CarToCarAnswerDto();
        carDto.setId(car.getId());
        carDto.setBrand(car.getBrand());
        carDto.setModel(car.getModel());
        carDto.setStateNumber(car.getStateNumber());

        carDto.setClientAnswerDto(clientDto(user));

        return carDto;
    }

    private ClientAnswerDto clientDto(Users user){
        ClientAnswerDto clientDto = new ClientAnswerDto();
        clientDto.setEmail(user.getEmail());
        clientDto.setNameUser(user.getName());
        return clientDto;
    }
}
