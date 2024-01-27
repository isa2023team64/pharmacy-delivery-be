package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.Coordinates;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoordinatesResponseDTO extends CoordinatesRequestDTO{

    public CoordinatesResponseDTO(Coordinates coordinates) {
        this(coordinates.getLatitude(), coordinates.getLongitude(), coordinates.getDeliveryId());
    }

    public CoordinatesResponseDTO(double latitude, double longitude, int deliveryId) {
        super(latitude, longitude, deliveryId);
    }
    
}
