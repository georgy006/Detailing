package com.example.detailing.controllers;

import com.example.detailing.persistence.models.Role;
import com.example.detailing.persistence.models.answers.TokenAnswerDto;
import com.example.detailing.persistence.models.answers.UserAnswerDto;
import com.example.detailing.persistence.models.requests.UserCreateRequestDto;
import com.example.detailing.persistence.repositories.UsersRepository;
import com.example.detailing.services.AuthService;
import com.example.detailing.services.UserService;
import com.example.detailing.services.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserAnswerDto> registerUser(@RequestBody UserCreateRequestDto userDto){
        return ResponseEntity.ok(userService.registerUser(userDto));
    }

    @PostMapping("/login")
    public TokenAnswerDto login(@RequestBody UserCreateRequestDto request) {
        return authService.login(request);
    }


}
