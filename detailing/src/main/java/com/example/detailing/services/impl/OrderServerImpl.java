package com.example.detailing.services.impl;

import com.example.detailing.persistence.models.Car;
import com.example.detailing.persistence.models.Orders;
import com.example.detailing.persistence.models.Services;
import com.example.detailing.persistence.models.Users;
import com.example.detailing.persistence.models.answers.order.*;
import com.example.detailing.persistence.models.requests.OrderCreateRequestDto;
import com.example.detailing.persistence.models.requests.OrderUpdateRequestDto;
import com.example.detailing.persistence.repositories.CarRepository;
import com.example.detailing.persistence.repositories.OrdersRepository;
import com.example.detailing.persistence.repositories.ServiceRepository;
import com.example.detailing.persistence.repositories.UsersRepository;
import com.example.detailing.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServerImpl implements OrderService {

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    ServiceRepository serviceRepository;

    private OrderAnswerDto convertToOrderAnswerDto(Orders order) {

        Car car = carRepository.findById(order.getCar().getId())
                .orElseThrow(() -> new RuntimeException("Car не найдено"));

        Users user = usersRepository.findById(order.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Car не найдено"));

        Services service = serviceRepository.findById(order.getServices().getId())
                .orElseThrow(() -> new RuntimeException("Service не найдено"));

        OrderAnswerDto answerDto = new OrderAnswerDto();
        answerDto.setId(order.getId());
        answerDto.setStatus(order.getStatus());

        //Авто
        CarAnswerDto carDto = new CarAnswerDto();
        carDto.setBrand(car.getBrand());
        carDto.setModel(car.getModel());
        carDto.setStateNumber(car.getStateNumber());
        answerDto.setCarAnswerDto(carDto);

        //Клиент
        ClientAnswerDto clientDto = new ClientAnswerDto();
        clientDto.setEmail(car.getUser().getEmail());
        clientDto.setNameUser(car.getUser().getName());
        answerDto.setClientAnswerDto(clientDto);

        //Стафф
        StaffAnswerDto staffDto = new StaffAnswerDto();
        staffDto.setNameStaff(user.getName());
        answerDto.setStaffAnswerDto(staffDto);

        //Услуга
        ServiceAnswerDto serviceDto = new ServiceAnswerDto();
        serviceDto.setNameService(service.getName());
        serviceDto.setPrice(service.getPrice());
        serviceDto.setTime(service.getTime());
        serviceDto.setDescription(service.getDescription());
        answerDto.setServiceAnswerDto(serviceDto);

        return answerDto;
    }

    @Override
    public List<OrderAnswerDto> getAllOrders() {
        List<Orders> orders = ordersRepository.findAll();

        return orders.stream()
                .map(this::convertToOrderAnswerDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderAnswerDto getOrdersById(Long id) {
        Orders order = ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Заказ не найден"));

        return convertToOrderAnswerDto(order);
    }

    @Override
    public List<OrderAnswerDto> getOrdersByUser(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        List<Orders> orders = ordersRepository.findByUser(user);

        return  orders.stream()
                .map(this::convertToOrderAnswerDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderAnswerDto> getOrdersByCar(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Авто не найдено"));

        List<Orders> orders = ordersRepository.findByCar(car);

        return orders.stream()
                .map(this::convertToOrderAnswerDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderAnswerDto addOrder(OrderCreateRequestDto orderDto) {
        Car car = carRepository.findById(orderDto.getCarId())
                .orElseThrow(() -> new RuntimeException("Машина не найдена"));

        Users user = usersRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Services service = serviceRepository.findById(orderDto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Услуга не найдена"));

        Orders order = new Orders(
                null,
                orderDto.getStatus(),
                car,
                service,
                user
        );

        return convertToOrderAnswerDto(ordersRepository.save(order));
    }

    @Override
    public OrderAnswerDto updateOrder(Long id, OrderUpdateRequestDto orderDto) {
        Orders order =ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Услуга не найдена"));

        order.setStatus(orderDto.getStatus());
        return convertToOrderAnswerDto(ordersRepository.save(order));
    }
}
