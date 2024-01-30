package com.isa2023team64.pharmacydeliverybe.service;

import java.util.Collection;
import java.util.List;

import com.isa2023team64.pharmacydeliverybe.dto.AppointmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegularReservationResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;
import com.isa2023team64.pharmacydeliverybe.model.Reservation;

public interface ReservationService {
    public RegularReservationResponseDTO create(int userId, int appointmentId, List<Integer> equipmentIds, List<Integer> quantities);
    public List<AppointmentResponseDTO> findAllUserAppointments(int userId);
    public void deleteReservation(int reservationId);
    public Collection<Reservation> getPendingByCompanyId(int companyId);
    public Collection<RegisteredUser> getUsersThanReserved();
    public void markReservationAsTaken(int reservationId);
    public Reservation findById(int id);
    

}
