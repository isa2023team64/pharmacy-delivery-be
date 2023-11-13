package com.isa2023team64.pharmacydeliverybe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class RegisteredUser extends User {
    
    @Column(nullable = false)
    @NotEmpty
    private String firstName;
    
    @Column(nullable = false)
    @NotEmpty
    private String lastName;

    public RegisteredUser() {
        super();
    }

    public RegisteredUser(Integer id, String username, String email, String password, String firstName, String lastName) {
        super(id, username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
