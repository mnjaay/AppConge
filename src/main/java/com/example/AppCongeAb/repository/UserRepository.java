package com.example.AppCongeAb.repository;

import com.example.AppCongeAb.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByEmailAndPassword(String email, String password);

}
