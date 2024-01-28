package com.isa2023team64.pharmacydeliverybe.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.repository.RegisteredUserRepository;
import com.isa2023team64.pharmacydeliverybe.repository.ReservationRepository;
import com.isa2023team64.pharmacydeliverybe.util.enums.ReservationStatus;

@Service
@EnableScheduling
public class PenaltyPointsBackgroundService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private RegisteredUserRepository registeredUserRepository;
    
    @Scheduled(fixedRate = 1000 * 60 * 60 * 6)
    private void givePenaltyPoints() {
        var reservations = reservationRepository.findAll().stream().filter(
            r -> r.getAppointment().getEndTime().isBefore(LocalDateTime.now()) &&
            r.getStatus().equals(ReservationStatus.PENDING)).toList();

        for (var reservation : reservations) {
            reservation.setStatus(ReservationStatus.EXPIRED);
            var user = reservation.getUser();
            int penaltyPoints = user.getPenaltyPoints() + 2;
            user.setPenaltyPoints(penaltyPoints);
            reservationRepository.save(reservation);
            registeredUserRepository.save(user);
        }
    }

}
