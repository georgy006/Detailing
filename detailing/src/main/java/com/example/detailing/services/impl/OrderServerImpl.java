package com.example.detailing.services.impl;

import com.example.detailing.persistence.models.Car;
import com.example.detailing.persistence.models.Orders;
import com.example.detailing.persistence.models.Services;
import com.example.detailing.persistence.models.Users;
import com.example.detailing.persistence.models.answers.*;
import com.example.detailing.persistence.models.requests.OrderCreateRequestDto;
import com.example.detailing.persistence.models.requests.OrderUpdateRequestDto;
import com.example.detailing.persistence.repositories.CarRepository;
import com.example.detailing.persistence.repositories.OrdersRepository;
import com.example.detailing.persistence.repositories.ServiceRepository;
import com.example.detailing.persistence.repositories.UsersRepository;
import com.example.detailing.services.OrderService;
import com.example.detailing.services.mapping.OrderConvertor;
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
    @Autowired
    private OrderConvertor orderConverter;


    @Override
    public List<OrderAnswerDto> getAllOrders() {
        List<Orders> orders = ordersRepository.findAll();

        return orders.stream()
                .map(orderConverter :: convertToOrderAnswerDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderAnswerDto getOrdersById(Long id) {
        Orders order = ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Заказ не найден"));

        return orderConverter.convertToOrderAnswerDto(order);
    }

    @Override
    public List<OrderAnswerDto> getOrdersByUser(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        List<Orders> orders = ordersRepository.findByUser(user);

        return  orders.stream()
                .map(orderConverter :: convertToOrderAnswerDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderAnswerDto> getOrdersByCar(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Авто не найдено"));

        List<Orders> orders = ordersRepository.findByCar(car);

        return orders.stream()
                .map(orderConverter :: convertToOrderAnswerDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderAnswerDto addOrder(OrderCreateRequestDto orderDto) {
        Car car = carRepository.findById(orderDto.getCarId())
                .orElseThrow(() -> new RuntimeException("Машина не найдена"));


        Services service = serviceRepository.findById(orderDto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Услуга не найдена"));

        Orders order = new Orders(
                null,
                orderDto.getStatus(),
                car,
                service,
                null
        );

        return orderConverter.convertToOrderAnswerDto(ordersRepository.save(order));
    }

    @Override
    public OrderAnswerDto updateOrder(Long id, OrderUpdateRequestDto orderDto) {
        Orders order = ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Услуга не найдена"));
        Users user = usersRepository.findById(order.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User не найдено"));
        OrderAnswerDto answerDto = new OrderAnswerDto();


        order.setStatus(orderDto.getStatus());
        order.setUser(user);
        return orderConverter.convertToOrderAnswerDto(ordersRepository.save(order));
    }
}
