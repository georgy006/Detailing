package com.example.detailing.services;

import com.example.detailing.persistence.models.Role;
import com.example.detailing.persistence.models.answers.RoleAnswerDto;

public interface RoleService {
    RoleAnswerDto getRoleById(Long id);

}
