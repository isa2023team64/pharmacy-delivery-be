package com.isa2023team64.pharmacydeliverybe.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa2023team64.pharmacydeliverybe.model.Appointment;
import com.isa2023team64.pharmacydeliverybe.model.Company;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    public Collection<Appointment> findByCompany(Company company);

}
