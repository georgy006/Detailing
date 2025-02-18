package com.example.detailing.controllers;

import com.example.detailing.persistence.models.Services;
import com.example.detailing.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @GetMapping
    public List<Services> getAllServices(){
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public Services getServiceById(@PathVariable Long id){
        return serviceService.getServiceById(id);
    }

}
