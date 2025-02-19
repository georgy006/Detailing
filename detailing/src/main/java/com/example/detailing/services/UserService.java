package com.example.detailing.services;

import com.example.detailing.persistence.models.Users;
import com.example.detailing.persistence.models.requests.UserCreateRequestDto;
import com.example.detailing.persistence.models.requests.UserUpdateRequestDto;

import java.util.List;

public interface UserService {
    List<Users> getAllClients();
    List<Users> getAllStaff();
    Users getUserById(Long id);
    Users addUser(UserCreateRequestDto userDto);
    Users updateUser(Long id, UserUpdateRequestDto userDto);
    void deleteUser(Long id);

}
