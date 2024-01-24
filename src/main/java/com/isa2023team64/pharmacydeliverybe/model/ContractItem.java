package com.isa2023team64.pharmacydeliverybe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ContractItem extends GenericEntity {
    @ManyToOne
    private Contract contract;

    @ManyToOne
    private Equipment equipment;

    private int quantity;
}
