package com.example.detailing.services.impl;

import com.example.detailing.persistence.models.Car;
import com.example.detailing.persistence.models.Users;
import com.example.detailing.persistence.models.answers.CarToCarAnswerDto;
import com.example.detailing.persistence.models.answers.ClientAnswerDto;
import com.example.detailing.persistence.models.requests.CarRequestDto;
import com.example.detailing.persistence.repositories.CarRepository;
import com.example.detailing.persistence.repositories.UsersRepository;
import com.example.detailing.services.CarService;
import com.example.detailing.services.mapping.CarConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CarConvertor carConvertor;


    @Override
    public List<CarToCarAnswerDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(carConvertor::convertCarToCarAnswerDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<CarToCarAnswerDto> getUserCars(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        List<Car> cars = carRepository.findByUser(user);
        return cars.stream()
                .map(carConvertor::convertCarToCarAnswerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarToCarAnswerDto getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Автомобиль не найден"));
        return carConvertor.convertCarToCarAnswerDto(car);
    }

    @Override
    public CarToCarAnswerDto addCarToUser(Long userId, CarRequestDto carDto) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Car car = new Car(null,
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getStateNumber(),
                user
        );

        return carConvertor.convertCarToCarAnswerDto(carRepository.save(car));
    }

    @Override
    public CarToCarAnswerDto updateCar(Long id, CarRequestDto carDto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Автомобиль не найден"));
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setStateNumber(carDto.getStateNumber());

        return carConvertor.convertCarToCarAnswerDto(carRepository.save(car));
    }

    @Override
    public void deleteCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Машина не найдена"));
        carRepository.delete(car);
    }
}
