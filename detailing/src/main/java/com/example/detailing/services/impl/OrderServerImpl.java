package com.example.detailing.services.impl;

import com.example.detailing.persistence.models.Car;
import com.example.detailing.persistence.models.Orders;
import com.example.detailing.persistence.models.Services;
import com.example.detailing.persistence.models.Users;
import com.example.detailing.persistence.models.dto.OrderCreateRequestDto;
import com.example.detailing.persistence.models.dto.OrderUpdateRequestDto;
import com.example.detailing.persistence.repositories.CarRepository;
import com.example.detailing.persistence.repositories.OrdersRepository;
import com.example.detailing.persistence.repositories.ServiceRepository;
import com.example.detailing.persistence.repositories.UsersRepository;
import com.example.detailing.services.OrderService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders getOrdersById(Long id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Заказ не найден"));
    }

    @Override
    public List<Orders> getOrdersByUser(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        return ordersRepository.findByUser(user);
    }

    @Override
    public List<Orders> getOrdersByCar(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Авто не найдено"));

        return ordersRepository.findByCar(car);
    }

    @Override
    public Orders addOrder(OrderCreateRequestDto orderDto) {
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

        return ordersRepository.save(order);
    }

    @Override
    public Orders updateOrder(Long id, OrderUpdateRequestDto orderDto) {
        Orders order =ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Услуга не найдена"));

        order.setStatus(orderDto.getStatus());
        return ordersRepository.save(order);
    }
}
