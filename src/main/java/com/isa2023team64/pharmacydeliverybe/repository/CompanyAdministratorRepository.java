package com.isa2023team64.pharmacydeliverybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;

public interface CompanyAdministratorRepository extends JpaRepository<CompanyAdministrator, Integer> {

    CompanyAdministrator findByEmail(String email);

    List<CompanyAdministrator> findByCompanyName(String companyName);
}
