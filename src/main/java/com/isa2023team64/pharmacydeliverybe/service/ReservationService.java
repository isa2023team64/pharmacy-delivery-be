package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import com.isa2023team64.pharmacydeliverybe.dto.RegularReservationResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.Appointment;

public interface ReservationService {
    public RegularReservationResponseDTO create(int userId, int appointmentId, List<Integer> equipmentIds, List<Integer> quantities);
    public List<Appointment> findAllUserAppointments(int userId);
    public void deleteReservation(int reservationId);
}
