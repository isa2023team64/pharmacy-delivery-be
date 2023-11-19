package com.isa2023team64.pharmacydeliverybe.dto;

import java.sql.Timestamp;

import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;

public class CompanyAdministratorResponseDTO extends CompanyAdministratorRequestDTO{
    private Integer id;

    public CompanyAdministratorResponseDTO() {
        super();
    }


    public CompanyAdministratorResponseDTO(CompanyAdministrator companyAdministrator){
        this(companyAdministrator.getId(), companyAdministrator.getEmail(), companyAdministrator.getPassword(), companyAdministrator.getFirstName(), 
        companyAdministrator.getLastName(),companyAdministrator.isActive(),companyAdministrator.getLastPasswordResetDate(),companyAdministrator.getCity(),
        companyAdministrator.getCountry(),companyAdministrator.getPhoneNumber(),companyAdministrator.getWorkplace(),companyAdministrator.getCompanyName());
    }

    public CompanyAdministratorResponseDTO(Integer id, String email, String password, String firstName, String lastName, 
    Boolean active, Timestamp lastPasswordResetDate, String city, String country,String phoneNumber, String workplace, String company) {
        
        super(email, password, firstName, lastName, city, country, phoneNumber, workplace, company);
        this.id = id;        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
