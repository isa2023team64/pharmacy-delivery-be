package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.mapper.EquipmentDTOMapper;
import com.isa2023team64.pharmacydeliverybe.model.ReservationItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationItemNoReservationResponseDTO {
 
    private int id;
    private int quantity;
    private EquipmentResponseDTO equipment;

    public ReservationItemNoReservationResponseDTO(ReservationItem reservationItem){
        this(reservationItem.getId() ,reservationItem.getQuantity(), EquipmentDTOMapper.toResponseDTO(reservationItem.getEquipment()));
    }


}
