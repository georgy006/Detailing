package com.example.detailing.persistence.repositories;

import com.example.detailing.persistence.models.Car;
import com.example.detailing.persistence.models.Orders;
import com.example.detailing.persistence.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCar(Car car);
    List<Orders> findByUser(Users user);
}
