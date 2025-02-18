package com.example.detailing.services;

import com.example.detailing.persistence.models.Services;
import com.example.detailing.persistence.models.dto.ServiceRequestDto;

import java.util.List;

public interface ServiceService {
    List<Services> getAllServices();
    Services getServiceById(Long id);
    Services addService(ServiceRequestDto serviceDto);
    void deleteService(Long id);
    }
