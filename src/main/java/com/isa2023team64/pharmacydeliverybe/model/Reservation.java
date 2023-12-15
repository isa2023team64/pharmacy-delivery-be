package com.isa2023team64.pharmacydeliverybe.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation extends GenericEntity {

    @Column
    @NotEmpty
    private boolean handovered;

    @Column
    @NotEmpty
    private boolean expired;

    @Column
    @NotEmpty
    private boolean equipmentTaken;

    @OneToOne(cascade = CascadeType.ALL)
    private Appointment appointment;

    @OneToOne(cascade = CascadeType.ALL)
    private RegisteredUser user;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Equipment> orderItems;

    public Reservation(boolean handovered, boolean expired, boolean equipmentTaken,
            Appointment appointment, RegisteredUser user, List<Equipment> orderItems) {
        this.handovered = handovered;
        this.expired = expired;
        this.equipmentTaken = equipmentTaken;
        this.appointment = appointment;
        this.user = user;
        this.orderItems = orderItems;
    }

    
}
