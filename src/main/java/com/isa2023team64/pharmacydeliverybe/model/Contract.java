package com.isa2023team64.pharmacydeliverybe.model;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Contract extends GenericEntity {
    
    @ManyToOne(cascade = CascadeType.ALL)
    public Hospital hospital;

    @Column
    private int day;

    @OneToMany(mappedBy = "contract", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Collection<ContractItem> orders;

}
