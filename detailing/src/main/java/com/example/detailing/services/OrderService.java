package com.example.detailing.services;

import com.example.detailing.persistence.models.Orders;
import com.example.detailing.persistence.models.dto.OrderCreateRequestDto;
import com.example.detailing.persistence.models.dto.OrderUpdateRequestDto;
import jakarta.persistence.criteria.Order;

import java.util.List;

public interface OrderService {
    List<Orders> getAllOrders();
    Orders getOrdersById(Long id);
    List<Orders> getOrdersByUser(Long userId);
    List<Orders> getOrdersByCar(Long carId);
    Orders addOrder(OrderCreateRequestDto userDto);
    Orders updateOrder(Long id, OrderUpdateRequestDto orderDto);
}
