package com.isa2023team64.pharmacydeliverybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    @Query("SELECT DISTINCT e FROM Equipment e JOIN FETCH e.companies c WHERE e.id = ?1")
    public Equipment findOneWithDistinctCompany(Integer equipmentId);
}
