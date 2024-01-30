package com.isa2023team64.pharmacydeliverybe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    public List<Equipment> findByCompanyId(Integer companyId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Equipment> findById(int id);

    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    <S extends Equipment> S save(S entity);

    // @Lock(LockModeType.PESSIMISTIC_WRITE)     
	// @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    // <S extends Equipment> S save(S entity);

}
