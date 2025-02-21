package com.example.detailing.services.impl;

import com.example.detailing.persistence.models.Users;
import com.example.detailing.persistence.models.answers.TokenAnswerDto;
import com.example.detailing.persistence.models.requests.UserCreateRequestDto;
import com.example.detailing.persistence.repositories.UsersRepository;
import com.example.detailing.services.AuthService;
import com.example.detailing.services.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;



    public AuthServiceImpl(JwtService jwtService, AuthenticationManager authenticationManager,
                           UsersRepository userRepository) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.usersRepository = userRepository;
    }

    @Override
    public TokenAnswerDto login(UserCreateRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        String token = jwtService.generateToken(request.getEmail());
        Users user = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        TokenAnswerDto tokenDto = new TokenAnswerDto();
        tokenDto.setId(user.getId());
        tokenDto.setToken(token);

        return tokenDto;
    }

}
