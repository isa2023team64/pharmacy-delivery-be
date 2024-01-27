package com.isa2023team64.pharmacydeliverybe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hospital extends GenericEntity {
    
    @Column
    @NotEmpty
    private String name;

    @Column
    @NotNull
    private double longitude;

    @Column
    @NotNull
    private double latitude;

    public Hospital(String name, double longitude, double latitude) {
        super();
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
