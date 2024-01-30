package com.isa2023team64.pharmacydeliverybe.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;

import jakarta.persistence.LockModeType;

public interface CompanyAdministratorRepository extends JpaRepository<CompanyAdministrator, Integer> {

    CompanyAdministrator findByEmail(String email);

    List<CompanyAdministrator> findByCompanyName(String companyName);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ca FROM CompanyAdministrator ca WHERE ca.company.id = :companyId")
    List<CompanyAdministrator> findAvailableAdministratorsForCompany(@Param("companyId") Integer companyId);
    
}
