package com.isa2023team64.pharmacydeliverybe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredUserUpdateDTO {

    private String password;
    private String passwordConfirmation;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String phoneNumber;
    private String workplace;
    private String companyName;
    private int penaltyPoints;    
}
