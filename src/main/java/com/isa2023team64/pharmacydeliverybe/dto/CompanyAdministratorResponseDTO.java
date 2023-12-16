package com.isa2023team64.pharmacydeliverybe.dto;

import java.sql.Timestamp;

import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyAdministratorResponseDTO extends CompanyAdministratorRequestDTO{
    
    private Integer id;
    private String fullName;
    private Integer companyId;
    private boolean firstLogin;

    public CompanyAdministratorResponseDTO(CompanyAdministrator companyAdministrator){
        this(companyAdministrator.getId(), companyAdministrator.getEmail(), companyAdministrator.getPassword(), companyAdministrator.getFirstName(), 
        companyAdministrator.getLastName(),companyAdministrator.isActive(),companyAdministrator.getLastPasswordResetDate(),companyAdministrator.getCity(),
        companyAdministrator.getCountry(),companyAdministrator.getPhoneNumber(),companyAdministrator.getWorkplace(),companyAdministrator.getCompanyName(), companyAdministrator.getFullName(), companyAdministrator.getCompany().getId(),
        companyAdministrator.isFirstLogin());
    }

    public CompanyAdministratorResponseDTO(Integer id, String email, String password, String firstName, String lastName, 
    Boolean active, Timestamp lastPasswordResetDate, String city, String country,String phoneNumber, String workplace, String company, String fullName, Integer companyId, boolean firstLogin) {
        super(email, password, firstName, lastName, city, country, phoneNumber, workplace, company);
        this.id = id;
        this.fullName = fullName;
        this.companyId = companyId;
        this.firstLogin = firstLogin;
    }

}
