package com.example.detailing.services.impl;

import com.example.detailing.persistence.models.Role;
import com.example.detailing.persistence.models.Users;
import com.example.detailing.persistence.models.requests.UserCreateRequestDto;
import com.example.detailing.persistence.models.requests.UserUpdateRequestDto;
import com.example.detailing.persistence.repositories.RoleRepository;
import com.example.detailing.persistence.repositories.UsersRepository;
import com.example.detailing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Users> getAllClients() {
        Role clientRole = roleRepository.findByName("client")
                .orElseThrow(() -> new RuntimeException("Роль 'client' не найдена"));
        return usersRepository.findByRole(clientRole);
    }

    @Override
    public List<Users> getAllStaff() {
        Role staffRole = roleRepository.findByName("staff")
                .orElseThrow(() -> new RuntimeException("Роль 'staff' не найдена"));
        return usersRepository.findByRole(staffRole);
    }

    @Override
    public Users getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    @Override
    public Users addUser(UserCreateRequestDto userDto) {
        Role role = roleRepository.findByName(userDto.getRole())
                .orElseThrow(() -> new RuntimeException("Роль"+ userDto.getRole() + "не найдена"));
        Users user = new Users(
                null,
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPassword(),
                role
                );
        return usersRepository.save(user);
    }

    @Override
    public Users updateUser(Long id, UserUpdateRequestDto userDto) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return usersRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        usersRepository.delete(user);
    }
}
