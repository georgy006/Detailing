package com.example.detailing.controllers;

import com.example.detailing.persistence.models.answers.OrderAnswerDto;
import com.example.detailing.persistence.models.requests.OrderCreateRequestDto;
import com.example.detailing.persistence.models.requests.OrderUpdateRequestDto;
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
    public List<OrderAnswerDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderAnswerDto> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrdersById(id));
    }

    @GetMapping("/user/staff/{staffId}")
    public List<OrderAnswerDto> getOrdersByStaff(@PathVariable Long staffId) {
        return orderService.getOrdersByUser(staffId);
    }
    @GetMapping("/user/client/{clientId}")
    public List<OrderAnswerDto> getOrdersByClient(@PathVariable Long clientId) {
        return orderService.getOrdersByClient(clientId);
    }

    @GetMapping("/car/{carId}")
    public List<OrderAnswerDto> getOrdersByCar(@PathVariable Long carId) {
        return orderService.getOrdersByCar(carId);
    }

    @PostMapping
    public ResponseEntity<OrderAnswerDto> addOrder(@RequestBody OrderCreateRequestDto orderDto) {
        return ResponseEntity.ok(orderService.addOrder(orderDto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<OrderAnswerDto> updateOrder(@PathVariable Long id,
                                              @RequestBody OrderUpdateRequestDto orderDto) {
        return ResponseEntity.ok(orderService.updateOrder(id, orderDto));
    }


}
