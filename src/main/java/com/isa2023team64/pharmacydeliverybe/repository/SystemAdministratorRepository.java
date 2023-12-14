package com.isa2023team64.pharmacydeliverybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;
import com.isa2023team64.pharmacydeliverybe.model.SystemAdministrator;

public interface SystemAdministratorRepository extends JpaRepository<SystemAdministrator, Integer> {
    RegisteredUser findByEmail(String email);
}
