package com.isa2023team64.pharmacydeliverybe.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.isa2023team64.pharmacydeliverybe.model.Appointment;
import com.isa2023team64.pharmacydeliverybe.model.Company;

import jakarta.persistence.LockModeType;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    public Collection<Appointment> findByCompany(Company company);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    <S extends Appointment> S save(S entity);

}
