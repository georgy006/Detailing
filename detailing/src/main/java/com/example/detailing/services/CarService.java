package com.example.detailing.services;

import com.example.detailing.persistence.models.Car;
import com.example.detailing.persistence.models.answers.car.CarToCarAnswerDto;
import com.example.detailing.persistence.models.requests.CarRequestDto;

import java.util.List;

public interface CarService {
    List<CarToCarAnswerDto> getAllCars();
    List<CarToCarAnswerDto> getUserCars(Long userId);
    CarToCarAnswerDto getCarById(Long id);
    CarToCarAnswerDto addCarToUser(Long userId, CarRequestDto carDto);
    CarToCarAnswerDto updateCar(Long id, CarRequestDto carDto);
    void deleteCar(Long id);
}
