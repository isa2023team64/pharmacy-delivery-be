package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.mapper.AppointmentDTOMapper;
import com.isa2023team64.pharmacydeliverybe.model.Reservation;
import com.isa2023team64.pharmacydeliverybe.util.enums.ReservationStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDTO {
    
    private int id;
    private ReservationStatus status;
    private AppointmentResponseDTO appointment;
    private RegisteredUserResponseDTO registeredUser;

    public ReservationResponseDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.status = reservation.getStatus();
        this.appointment = AppointmentDTOMapper.toResponseDTO(reservation.getAppointment());
        this.registeredUser = new RegisteredUserResponseDTO(reservation.getUser());
    }

}
