package com.example.detailing.services;

import com.example.detailing.persistence.models.Car;
import com.example.detailing.persistence.models.requests.CarRequestDto;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    List<Car> getUserCars(Long userId);
    Car getCarById(Long id);
    Car addCarToUser(Long userId, CarRequestDto carDto);
    Car updateCar(Long id, CarRequestDto carDto);
    void deleteCar(Long id);
}
