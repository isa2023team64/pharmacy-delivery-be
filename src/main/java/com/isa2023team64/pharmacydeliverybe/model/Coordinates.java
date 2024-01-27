package com.isa2023team64.pharmacydeliverybe.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {

    private double latitude;
    private double longitude;
    private int deliveryId;

}
