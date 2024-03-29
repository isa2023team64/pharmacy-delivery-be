package com.isa2023team64.pharmacydeliverybe.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtraordinaryReservationRequestDTO {
    private AppointmentRequestDTO appointment;
    
    private List<Integer> equipmentIds;
    private List<Integer> equipmentQuantities;
    private int userId;
}
