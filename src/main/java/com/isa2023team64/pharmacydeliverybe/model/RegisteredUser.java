package com.isa2023team64.pharmacydeliverybe.model;

import jakarta.persistence.Entity;

@Entity
public class RegisteredUser extends User {
    
    public RegisteredUser() {
        super();
    }

    public RegisteredUser(Integer id, String username, String email, String password, String firstName, String lastName, boolean active, String city, String country,String phoneNumber, String workplace, String company) {
        super(id, email, password, firstName, lastName,active, city,country,phoneNumber,workplace,company);
    }

}
