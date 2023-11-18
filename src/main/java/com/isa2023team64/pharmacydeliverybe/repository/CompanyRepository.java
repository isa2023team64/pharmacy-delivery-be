package com.isa2023team64.pharmacydeliverybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isa2023team64.pharmacydeliverybe.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    
    @Query("select c from Company c join fetch c.equipment e where c.id =?1")
    public Company findOneWithEquipment(Integer companyId);

    @Query("SELECT DISTINCT c FROM Company c JOIN c.equipment e WHERE e.id IN :equipmentIds")
    List<Company> findCompaniesByEquipmentIds(@Param("equipmentIds") List<Integer> equipmentIds);
}
