package com.isa2023team64.pharmacydeliverybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;

public interface CompanyAdministratorRepository extends JpaRepository<CompanyAdministrator, Integer> {

    CompanyAdministrator findByEmail(String email);
    
}
