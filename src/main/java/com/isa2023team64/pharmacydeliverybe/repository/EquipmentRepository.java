package com.isa2023team64.pharmacydeliverybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    @Query("SELECT DISTINCT e FROM Equipment e JOIN FETCH e.companies c WHERE e.id = ?1")
    public Equipment findOneWithDistinctCompany(Integer equipmentId);

    @Query("SELECT e FROM Equipment e JOIN FETCH e.companies c WHERE c.id = :companyId")
    public List<Equipment> findEquipmentByCompanyId(@Param("companyId") Integer companyId);
}