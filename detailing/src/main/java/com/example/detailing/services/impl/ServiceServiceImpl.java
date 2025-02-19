package com.example.detailing.services.impl;

import com.example.detailing.persistence.models.Services;
import com.example.detailing.persistence.models.requests.ServiceRequestDto;
import com.example.detailing.persistence.repositories.ServiceRepository;
import com.example.detailing.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Services getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Услуга не найдена"));
    }

    @Override
    public Services addService(ServiceRequestDto serviceDto) {
        return null;
    }

    @Override
    public void deleteService(Long id) {

    }
}
