package com.isa2023team64.pharmacydeliverybe.dto;

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
public class RegularReservationNoRegisteredUserDTO {
    
    private Integer id;
    private ReservationStatus status;
    private AppointmentResponseDTO appointment;
    private Integer userId;
    private String qrCode;

        public RegularReservationNoRegisteredUserDTO(Reservation reservation, AppointmentResponseDTO appointment) {
        this.id = reservation.getId();
        this.status = reservation.getStatus();
        this.appointment = appointment;
        this.userId = reservation.getUser().getId();
        this.qrCode = reservation.getQrCode();
    }


}
