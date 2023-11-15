package com.isa2023team64.pharmacydeliverybe.model;

import jakarta.persistence.Entity;

@Entity
public class RegisteredUser extends User {
    
    public RegisteredUser() {
        super();
    }

    public RegisteredUser(Integer id, String username, String email, String password, String firstName, String lastName, boolean active) {
        super(id, email, password, firstName, lastName,active);
    }

}
