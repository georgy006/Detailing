package com.example.detailing.persistence.repositories;

import com.example.detailing.persistence.models.Car;
import com.example.detailing.persistence.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByUser(Users user);
}
