package com.isa2023team64.pharmacydeliverybe.service;

import com.isa2023team64.pharmacydeliverybe.dto.RegularReservationRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegularReservationResponseDTO;

public interface ReservationService {
    public RegularReservationResponseDTO createRegular(RegularReservationRequestDTO dto);

}
