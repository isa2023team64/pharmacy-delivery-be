package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.Coordinates;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoordinatesResponseDTO extends CoordinatesRequestDTO{

    private Integer id;

    public CoordinatesResponseDTO(Coordinates coordinates) {
        this(coordinates.getId(), coordinates.getLatitude(), coordinates.getLongitude());
    }

    public CoordinatesResponseDTO(Integer id, double latitude, double longitude) {
        super(latitude, longitude);
        this.id = id;
    }
    
}
