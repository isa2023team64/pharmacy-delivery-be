package com.isa2023team64.pharmacydeliverybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa2023team64.pharmacydeliverybe.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
