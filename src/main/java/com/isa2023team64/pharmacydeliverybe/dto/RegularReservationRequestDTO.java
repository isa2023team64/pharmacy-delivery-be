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
public class RegularReservationRequestDTO {
    
    private List<Integer> equipmentIds;
    private int appointmentId;
    private int userId;
}
