package com.example.detailing.services;

import com.example.detailing.persistence.models.answers.order.OrderAnswerDto;
import com.example.detailing.persistence.models.requests.OrderCreateRequestDto;
import com.example.detailing.persistence.models.requests.OrderUpdateRequestDto;

import java.util.List;

public interface OrderService {
    List<OrderAnswerDto> getAllOrders();
    OrderAnswerDto getOrdersById(Long id);
    List<OrderAnswerDto> getOrdersByUser(Long userId);
    List<OrderAnswerDto> getOrdersByCar(Long carId);
    OrderAnswerDto addOrder(OrderCreateRequestDto userDto);
    OrderAnswerDto updateOrder(Long id, OrderUpdateRequestDto orderDto);
}
