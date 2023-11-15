package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;

public class RegisteredUserRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public RegisteredUserRequestDTO() {
        super();
    }

    public RegisteredUserRequestDTO(RegisteredUser registeredUser) {
        this(registeredUser.getEmail(), registeredUser.getPassword(), registeredUser.getFirstName(), registeredUser.getLastName());
    }

    public RegisteredUserRequestDTO(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;        
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
