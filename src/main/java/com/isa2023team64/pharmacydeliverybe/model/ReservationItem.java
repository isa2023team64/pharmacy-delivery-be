package com.isa2023team64.pharmacydeliverybe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationItem extends GenericEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    private Reservation reservation;

    @ManyToOne
    private Equipment equipment;

    @Column
    @NotEmpty
    private int quantity;
}
