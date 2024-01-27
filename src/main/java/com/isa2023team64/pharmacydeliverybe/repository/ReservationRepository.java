package com.isa2023team64.pharmacydeliverybe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa2023team64.pharmacydeliverybe.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Optional<Reservation> findByAppointmentId(int appointmentId);

}
