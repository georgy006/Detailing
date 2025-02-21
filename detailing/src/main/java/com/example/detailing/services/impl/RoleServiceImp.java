package com.example.detailing.services.impl;

import com.example.detailing.persistence.models.Users;
import com.example.detailing.persistence.models.answers.RoleAnswerDto;
import com.example.detailing.persistence.repositories.UsersRepository;
import com.example.detailing.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public RoleAnswerDto getRoleById(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        RoleAnswerDto roleDto = new RoleAnswerDto();
        roleDto.setNameRole(user.getRole().getName());
        return roleDto;
    }
}
