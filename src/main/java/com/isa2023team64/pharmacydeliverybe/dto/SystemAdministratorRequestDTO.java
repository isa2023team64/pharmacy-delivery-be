package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.SystemAdministrator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemAdministratorRequestDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String phoneNumber;

    public SystemAdministratorRequestDTO(SystemAdministrator systemAdministrator ){
        this(systemAdministrator.getEmail(), systemAdministrator.getPassword(), systemAdministrator.getFirstName(), systemAdministrator.getLastName(),systemAdministrator.getCity(),systemAdministrator.getCountry(),systemAdministrator.getPhoneNumber());
    }

}
