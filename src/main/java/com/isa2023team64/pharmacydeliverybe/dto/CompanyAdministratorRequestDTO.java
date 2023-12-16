package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyAdministratorRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String phoneNumber;
    private String workplace;
    private String companyName;

    public CompanyAdministratorRequestDTO(CompanyAdministrator companyAdministrator){
        this(companyAdministrator.getEmail(), companyAdministrator.getPassword(),
             companyAdministrator.getFirstName(), companyAdministrator.getLastName(),
             companyAdministrator.getCity(),companyAdministrator.getCountry(),
             companyAdministrator.getPhoneNumber(),companyAdministrator.getWorkplace(),
             companyAdministrator.getCompanyName());
    }

}
