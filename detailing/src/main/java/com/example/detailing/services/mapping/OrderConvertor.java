package com.example.detailing.services.mapping;

import com.example.detailing.persistence.models.Car;
import com.example.detailing.persistence.models.Orders;
import com.example.detailing.persistence.models.Services;
import com.example.detailing.persistence.models.Users;
import com.example.detailing.persistence.models.answers.*;
import com.example.detailing.persistence.repositories.CarRepository;
import com.example.detailing.persistence.repositories.ServiceRepository;
import com.example.detailing.persistence.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderConvertor {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public OrderAnswerDto convertToOrderAnswerDto(Orders order) {
        Car car = carRepository.findById(order.getCar().getId())
                .orElseThrow(() -> new RuntimeException("Car не найдено"));



        Services service = serviceRepository.findById(order.getServices().getId())
                .orElseThrow(() -> new RuntimeException("Service не найдено"));

        OrderAnswerDto answerDto = new OrderAnswerDto();
        answerDto.setId(order.getId());
        answerDto.setStatus(order.getStatus());

        answerDto.setCarAnswerDto(convertCar(car));
        answerDto.setClientAnswerDto(convertClient(car));

        if(order.getUser() != null) {
            answerDto.setStaffAnswerDto(convertStaff(order.getUser()));
        }

        answerDto.setServiceAnswerDto(convertService(service));

        return answerDto;
    }
    private CarAnswerDto convertCar(Car car){
        CarAnswerDto carDto = new CarAnswerDto();
        carDto.setBrand(car.getBrand());
        carDto.setModel(car.getModel());
        carDto.setStateNumber(car.getStateNumber());
        return carDto;
    }
    private ClientAnswerDto convertClient(Car car){
        ClientAnswerDto clientDto = new ClientAnswerDto();
        clientDto.setEmail(car.getUser().getEmail());
        clientDto.setNameUser(car.getUser().getName());
        return clientDto;
    }
    private StaffAnswerDto convertStaff(Users user) {
        StaffAnswerDto staffDto = new StaffAnswerDto();
        staffDto.setNameStaff(user.getName());
        return staffDto;
    }

    private ServiceAnswerDto convertService(Services service){
        ServiceAnswerDto serviceDto = new ServiceAnswerDto();

        serviceDto.setNameService(service.getName());
        serviceDto.setPrice(service.getPrice());
        serviceDto.setTime(service.getTime());
        serviceDto.setDescription(service.getDescription());

        return serviceDto;
    }
}
