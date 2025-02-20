package com.example.detailing.persistence.repositories;

import com.example.detailing.persistence.models.Role;
import com.example.detailing.persistence.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByRole(Role role);
    Optional<Users> findByEmail(String email);

}
