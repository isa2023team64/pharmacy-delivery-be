package com.isa2023team64.pharmacydeliverybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    
    @Query("select c from Company c join fetch c.equipment e where c.id =?1")
    public Company findOneWithEquipment(Integer companyId);

    @Query("SELECT DISTINCT c FROM Company c JOIN c.equipment e WHERE e.id IN :equipmentIds")
    List<Company> findCompaniesByEquipmentIds(@Param("equipmentIds") List<Integer> equipmentIds);

    @Query("SELECT DISTINCT c FROM Company c JOIN c.equipment e WHERE e.id = :equipmentId GROUP BY c HAVING COUNT(e) = 1")
    List<Company> findCompaniesWithSingleEquipmentById(@Param("equipmentId") Integer equipmentId); 
    
    @Query("SELECT ca FROM CompanyAdministrator ca WHERE ca.company.id = :companyId")
    List<CompanyAdministrator> findCompanyAdministratorsByCompanyId(@Param("companyId") Integer companyId);

    @Query("SELECT ca.company FROM CompanyAdministrator ca WHERE ca.id = :companyAdministratorId")
    public Company findCompanyByAdministratorId(@Param("companyAdministratorId") Integer companyAdministratorId);
    
}
