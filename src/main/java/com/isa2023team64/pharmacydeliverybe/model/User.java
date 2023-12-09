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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor
public abstract class User extends GenericEntity {

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Column(nullable = false) 
    @NotEmpty
    private String password;

    @Column()
    @NotEmpty
    private String firstName;

    @Column()
    @NotEmpty
    private String lastName;

    @Column()
    private boolean active;

    @Column()
    private Timestamp lastPasswordResetDate;

    public User(Integer id, @Email String email, @NotEmpty String password, @NotEmpty String firstName, @NotEmpty String lastName, boolean active) {
        super(id);
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        Timestamp now = new Timestamp(new Date().getTime());
        this.setLastPasswordResetDate(now);
        this.password = password;
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
