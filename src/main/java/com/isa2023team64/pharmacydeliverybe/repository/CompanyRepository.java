package com.isa2023team64.pharmacydeliverybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isa2023team64.pharmacydeliverybe.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    
    @Query("select c from Company c join fetch c.equipment e where c.id =?1")
    public Company findOneWithEquipment(Integer companyId);
}
