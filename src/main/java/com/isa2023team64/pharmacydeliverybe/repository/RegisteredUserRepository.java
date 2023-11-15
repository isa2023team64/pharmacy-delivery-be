package com.isa2023team64.pharmacydeliverybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Integer> {

    RegisteredUser findByEmail(String email);

    RegisteredUser getById(int id);

    //RegisteredUser save(RegisteredUser u);
    
}
