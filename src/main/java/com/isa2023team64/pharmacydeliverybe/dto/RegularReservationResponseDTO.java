package com.isa2023team64.pharmacydeliverybe.dto;

import java.util.List;

import com.isa2023team64.pharmacydeliverybe.model.Reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegularReservationResponseDTO {
    private Integer id;
    private boolean handovered;
    private boolean expired;
    private boolean equipmentTaken;
    private AppointmentResponseDTO appointment;
    private RegisteredUserResponseDTO user;
    private List<EquipmentResponseDTO> orderItems;
    private String qrCode;

    public RegularReservationResponseDTO(Reservation reservation, AppointmentResponseDTO appointment, RegisteredUserResponseDTO user, List<EquipmentResponseDTO> orderItems, String qrCode) {
        this.id = reservation.getId();
        this.handovered = reservation.isHandovered();
        this.expired = reservation.isExpired();
        this.equipmentTaken = reservation.isEquipmentTaken();
        this.appointment = appointment;
        this.user = user;
        this.orderItems = orderItems;
        this.qrCode = qrCode;
    }
}
