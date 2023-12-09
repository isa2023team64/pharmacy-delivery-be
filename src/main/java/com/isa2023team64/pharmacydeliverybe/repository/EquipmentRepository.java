package com.isa2023team64.pharmacydeliverybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    public List<Equipment> findByCompanyId(Integer companyId);

}
