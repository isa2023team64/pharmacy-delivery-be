package com.isa2023team64.pharmacydeliverybe.dto;

import java.sql.Timestamp;

import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisteredUserResponseDTO extends RegisteredUserRequestDTO {

    private Integer id;
    private boolean active;
    private Timestamp lastPasswordResetDate;

    public RegisteredUserResponseDTO(RegisteredUser registeredUser) {
        this(registeredUser.getId(), registeredUser.getEmail(), registeredUser.getPassword(), registeredUser.getFirstName(), registeredUser.getLastName(),registeredUser.isActive(),registeredUser.getLastPasswordResetDate(),registeredUser.getCity(),registeredUser.getCountry(),registeredUser.getPhoneNumber(),registeredUser.getWorkplace(),registeredUser.getCompanyName());
    }

    public RegisteredUserResponseDTO(Integer id, String email, String password, String firstName, String lastName, Boolean active, Timestamp lastPasswordResetDate, String city, String country,String phoneNumber, String workplace, String company) {
        super(email, password, firstName, lastName, city, country, phoneNumber, workplace, company);

        this.id = id;
        this.active = active;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

}    
