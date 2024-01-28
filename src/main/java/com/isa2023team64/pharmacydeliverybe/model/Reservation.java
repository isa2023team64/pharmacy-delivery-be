package com.isa2023team64.pharmacydeliverybe.model;

import java.util.List;

import com.isa2023team64.pharmacydeliverybe.util.enums.ReservationStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation extends GenericEntity {

    @Enumerated
    private ReservationStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    private Appointment appointment;

    @ManyToOne(cascade = CascadeType.ALL)
    private RegisteredUser user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ReservationItem> orderItems;

    @Column
    private String qrCode;

    public Reservation(ReservationStatus status, Appointment appointment, RegisteredUser user, List<ReservationItem> orderItems) {
        this.status = status;
        this.appointment = appointment;
        this.user = user;
        this.orderItems = orderItems;
    }

}
