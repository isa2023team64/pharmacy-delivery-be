package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.Hospital;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HospitalResponseDTO {

    private int id;

    private String name;

    private double longitude;

    private double latitude;

    public HospitalResponseDTO(Hospital hospital) {
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.longitude = hospital.getLongitude();
        this.latitude = hospital.getLatitude();
    }
}
