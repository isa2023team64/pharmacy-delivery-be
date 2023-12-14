package com.isa2023team64.pharmacydeliverybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;
import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;

public interface CompanyAdministratorRepository extends JpaRepository<CompanyAdministrator, Integer> {
    RegisteredUser findByEmail(String email);
}
