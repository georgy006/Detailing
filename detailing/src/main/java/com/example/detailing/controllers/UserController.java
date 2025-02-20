package com.example.detailing.controllers;

import com.example.detailing.persistence.models.answers.UserAnswerDto;
import com.example.detailing.persistence.models.requests.UserCreateRequestDto;
import com.example.detailing.persistence.models.requests.UserUpdateRequestDto;
import com.example.detailing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/clients")
    public List<UserAnswerDto> getAllClients(){
        return userService.getAllClients();
    }
    @GetMapping("/staff")
    public List<UserAnswerDto> getAllStaff(){
        return userService.getAllStaff();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAnswerDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<UserAnswerDto> registerUser(@RequestBody UserCreateRequestDto userDto){
        return ResponseEntity.ok(userService.registerUser(userDto));
    }
    @PostMapping("/{id}")
    public ResponseEntity<UserAnswerDto> updateUser(@PathVariable Long id,
                                            @RequestBody UserUpdateRequestDto userDto){
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

