package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredUserRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String phoneNumber;
    private String workplace;
    private String companyName;
    private int penaltyPoints;

    public RegisteredUserRequestDTO(RegisteredUser registeredUser) {
        this(registeredUser.getEmail(), registeredUser.getPassword(), registeredUser.getFirstName(), registeredUser.getLastName(),registeredUser.getCity(),registeredUser.getCountry(),registeredUser.getPhoneNumber(),registeredUser.getWorkplace(),registeredUser.getCompanyName(),registeredUser.getPenaltyPoints());
    }

}
