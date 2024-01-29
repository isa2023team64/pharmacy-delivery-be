package com.isa2023team64.pharmacydeliverybe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;

import jakarta.persistence.LockModeType;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    public List<Equipment> findByCompanyId(Integer companyId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Equipment> findById(int id);

    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    <S extends Equipment> S save(S entity);

}
