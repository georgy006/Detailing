package com.example.detailing.services.impl;

import com.example.detailing.persistence.models.Role;
import com.example.detailing.persistence.models.Users;
import com.example.detailing.persistence.models.answers.UserAnswerDto;
import com.example.detailing.persistence.models.requests.UserCreateRequestDto;
import com.example.detailing.persistence.models.requests.UserUpdateRequestDto;
import com.example.detailing.persistence.repositories.RoleRepository;
import com.example.detailing.persistence.repositories.UsersRepository;
import com.example.detailing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    private UserAnswerDto convertToUserAnswerDto(Users user){
        UserAnswerDto userDto = new UserAnswerDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    @Override
    public List<UserAnswerDto> getAllClients() {
        Role clientRole = roleRepository.findByName("client")
                .orElseThrow(() -> new RuntimeException("Роль 'client' не найдена"));
        List<Users> users = usersRepository.findByRole(clientRole);
        return users.stream()
                .map(this::convertToUserAnswerDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserAnswerDto> getAllStaff() {
        Role staffRole = roleRepository.findByName("staff")
                .orElseThrow(() -> new RuntimeException("Роль 'staff' не найдена"));
        List<Users> users = usersRepository.findByRole(staffRole);
        return users.stream()
                .map(this::convertToUserAnswerDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserAnswerDto getUserById(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return convertToUserAnswerDto(user);
    }

    @Override
    public UserAnswerDto registerUser(UserCreateRequestDto userDto) {
        Role role = roleRepository.findByName(userDto.getRole())
                .orElseThrow(() -> new RuntimeException("Роль"+ userDto.getRole() + "не найдена"));

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        Users user = new Users(
                null,
                userDto.getName(),
                userDto.getEmail(),
                encodedPassword,
                role
                );
        return convertToUserAnswerDto(usersRepository.save(user));
    }

    @Override
    public UserAnswerDto updateUser(Long id, UserUpdateRequestDto userDto) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return convertToUserAnswerDto(usersRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        usersRepository.delete(user);
    }
}
