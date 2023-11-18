package com.isa2023team64.pharmacydeliverybe.model;

import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class SystemAdministrator extends User {

    public SystemAdministrator() {
        super();
    }

    public SystemAdministrator(Integer id, String email, String password, String firstName, String lastName,
    boolean active, Timestamp lastPasswordResetDate, String city, String country, String phoneNumber, String workplace, String companyName) {
        super(id, email, password, firstName, lastName, active, city, country, phoneNumber, workplace, companyName);
    }



}
