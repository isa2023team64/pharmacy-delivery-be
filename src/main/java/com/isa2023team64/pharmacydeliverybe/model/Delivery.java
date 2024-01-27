package com.isa2023team64.pharmacydeliverybe.model;

import java.time.LocalDate;

import com.isa2023team64.pharmacydeliverybe.util.enums.DeliveryStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery extends GenericEntity {
 
    @ManyToOne(fetch = FetchType.EAGER)
    private Hospital hospital;

    @Column
    @NotNull
    private LocalDate deliveryDate;

    @Column
    @NotNull
    private DeliveryStatus status;

    @Column
    private double longitude;

    @Column
    private double latitude;

}
