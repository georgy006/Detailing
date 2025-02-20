package com.example.detailing.services;

import com.example.detailing.persistence.models.answers.UserAnswerDto;
import com.example.detailing.persistence.models.requests.UserCreateRequestDto;
import com.example.detailing.persistence.models.requests.UserUpdateRequestDto;

import java.util.List;

public interface UserService {
    List<UserAnswerDto> getAllClients();
    List<UserAnswerDto> getAllStaff();
    UserAnswerDto getUserById(Long id);
    UserAnswerDto registerUser(UserCreateRequestDto userDto);
    UserAnswerDto updateUser(Long id, UserUpdateRequestDto userDto);
    void deleteUser(Long id);

}
