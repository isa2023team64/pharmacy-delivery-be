package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import com.isa2023team64.pharmacydeliverybe.dto.RegularReservationResponseDTO;

public interface ReservationService {
    public RegularReservationResponseDTO create(int userId, int appointmentId, List<Integer> equipmentIds);

}
