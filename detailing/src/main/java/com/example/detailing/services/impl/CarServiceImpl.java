package com.example.detailing.services.impl;

import com.example.detailing.persistence.models.Car;
import com.example.detailing.persistence.models.Users;
import com.example.detailing.persistence.models.requests.CarRequestDto;
import com.example.detailing.persistence.repositories.CarRepository;
import com.example.detailing.persistence.repositories.UsersRepository;
import com.example.detailing.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getUserCars(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return carRepository.findByUser(user);
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Автомобиль не найден"));
    }

    @Override
    public Car addCarToUser(Long userId, CarRequestDto carDto) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Car car = new Car(null,
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getStateNumber(),
                user
        );

        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, CarRequestDto carDto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Автомобиль не найден"));
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setStateNumber(carDto.getStateNumber());

        return carRepository.save(car);
    }

    @Override
    public void deleteCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Машина не найдена"));
        carRepository.delete(car);
    }
}
