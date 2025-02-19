package com.example.detailing.controllers;

import com.example.detailing.persistence.models.Car;
import com.example.detailing.persistence.models.requests.CarRequestDto;
import com.example.detailing.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping
    public List<Car> getAllCars(){
        return carService.getAllCars();
    }

    @GetMapping("/user/{userId}")
    public List<Car> getUserCars(@PathVariable Long userId){
        return carService.getUserCars(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Car> addCarToUser(@PathVariable Long userId,
                                      @RequestBody CarRequestDto carDto){
        return ResponseEntity.ok(carService.addCarToUser(userId, carDto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id,
                                            @RequestBody CarRequestDto carDto){
        return ResponseEntity.ok(carService.updateCar(id, carDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

}
