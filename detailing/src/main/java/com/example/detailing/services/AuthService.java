package com.example.detailing.services;

import com.example.detailing.persistence.models.Role;
import com.example.detailing.persistence.models.answers.TokenAnswerDto;
import com.example.detailing.persistence.models.requests.UserCreateRequestDto;

public interface AuthService {
    TokenAnswerDto login(UserCreateRequestDto request);
}
