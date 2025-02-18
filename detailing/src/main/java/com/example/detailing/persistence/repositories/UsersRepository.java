package com.example.detailing.persistence.repositories;

import com.example.detailing.persistence.models.Role;
import com.example.detailing.persistence.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByRole(Role role);

}
