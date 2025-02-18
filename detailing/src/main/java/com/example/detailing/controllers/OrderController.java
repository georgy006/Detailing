package com.example.detailing.controllers;

import com.example.detailing.persistence.models.Orders;
import com.example.detailing.persistence.models.dto.OrderCreateRequestDto;
import com.example.detailing.persistence.models.dto.OrderUpdateRequestDto;
import com.example.detailing.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrdersById(id));
    }

    @GetMapping("/user/{userId}")
    public List<Orders> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }

    @GetMapping("/car/{carId}")
    public List<Orders> getOrdersByCar(@PathVariable Long carId) {
        return orderService.getOrdersByCar(carId);
    }

    @PostMapping
    public ResponseEntity<Orders> addOrder(@RequestBody OrderCreateRequestDto orderDto) {
        return ResponseEntity.ok(orderService.addOrder(orderDto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id,
                                              @RequestBody OrderUpdateRequestDto orderDto) {
        return ResponseEntity.ok(orderService.updateOrder(id, orderDto));
    }


}
