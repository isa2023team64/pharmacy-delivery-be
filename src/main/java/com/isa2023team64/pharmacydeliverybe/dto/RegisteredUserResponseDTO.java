package com.isa2023team64.pharmacydeliverybe.dto;

import java.sql.Timestamp;

import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;

public class RegisteredUserResponseDTO extends RegisteredUserRequestDTO {
    private Integer id;
    private boolean active;
    private Timestamp lastPasswordResetDate;

    public RegisteredUserResponseDTO() {
        super();
    }

    public RegisteredUserResponseDTO(RegisteredUser registeredUser) {
        this(registeredUser.getId(), registeredUser.getEmail(), registeredUser.getPassword(), registeredUser.getFirstName(), registeredUser.getLastName(),registeredUser.isActive(),registeredUser.getLastPasswordResetDate(),registeredUser.getCity(),registeredUser.getCountry(),registeredUser.getPhoneNumber(),registeredUser.getWorkplace(),registeredUser.getCompany());
    }

    public RegisteredUserResponseDTO(Integer id, String email, String password, String firstName, String lastName, Boolean active, Timestamp lastPasswordResetDate, String city, String country,String phoneNumber, String workplace, String company) {
        super(email, password, firstName, lastName, city, country, phoneNumber, workplace, company);

        this.id = id;
        this.active = active;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
}    
