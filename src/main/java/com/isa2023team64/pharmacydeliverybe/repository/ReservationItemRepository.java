package com.isa2023team64.pharmacydeliverybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa2023team64.pharmacydeliverybe.model.ReservationItem;

public interface ReservationItemRepository extends JpaRepository<ReservationItem, Integer> {
    
    List<ReservationItem> findByReservationId(Integer reservationId);    

}
