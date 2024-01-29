package com.isa2023team64.pharmacydeliverybe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.isa2023team64.pharmacydeliverybe.model.Reservation;

import jakarta.persistence.LockModeType;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    public Optional<Reservation> findByAppointmentId(int appointmentId);
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    <S extends Reservation> S save(S entity);
    
    // @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<Reservation> findById(Integer id);

}
