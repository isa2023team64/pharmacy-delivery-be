package com.isa2023team64.pharmacydeliverybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa2023team64.pharmacydeliverybe.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}

