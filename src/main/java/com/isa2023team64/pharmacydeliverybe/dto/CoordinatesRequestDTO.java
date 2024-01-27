package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.Coordinates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesRequestDTO {
    
    private double latitude;
    private double longitude;
    private int deliveryId;

    public CoordinatesRequestDTO(Coordinates coordinates){
        this(coordinates.getLatitude(), coordinates.getLongitude(), coordinates.getDeliveryId());
    }

}
