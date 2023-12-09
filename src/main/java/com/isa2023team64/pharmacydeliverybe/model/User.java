package com.isa2023team64.pharmacydeliverybe.model;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends GenericEntity {

    @Column(name = "email", unique = true, nullable = false)
    @Email
    private String email;

    @Column(name = "password",nullable = false) 
    @NotEmpty
    private String password;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(name = "active")
    private boolean active;

    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

    public User() {
        super();
    }

    public User(Integer id, @Email String email, @NotEmpty String password, @NotEmpty String firstName, @NotEmpty String lastName, boolean active) {
        super(id);
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        Timestamp now = new Timestamp(new Date().getTime());
        this.setLastPasswordResetDate(now);
        this.password = password;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
