package com.example.detailing.controllers;

import com.example.detailing.persistence.models.Role;
import com.example.detailing.persistence.models.answers.RoleAnswerDto;
import com.example.detailing.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/{id}")
    public RoleAnswerDto getRoleById(@PathVariable Long id){
        return roleService.getRoleById(id);
    }
}
