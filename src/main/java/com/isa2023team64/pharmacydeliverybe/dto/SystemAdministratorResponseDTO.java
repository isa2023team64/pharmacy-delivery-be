package com.isa2023team64.pharmacydeliverybe.dto;

import java.sql.Timestamp;

import com.isa2023team64.pharmacydeliverybe.model.SystemAdministrator;

public class SystemAdministratorResponseDTO extends SystemAdministratorRequestDTO{
    private Integer id;

    public SystemAdministratorResponseDTO() {
        super();
    }


    public SystemAdministratorResponseDTO(SystemAdministrator systemAdministrator){
        this(systemAdministrator.getId(), systemAdministrator.getEmail(), systemAdministrator.getPassword(), systemAdministrator.getFirstName(), 
        systemAdministrator.getLastName(),systemAdministrator.isActive(),systemAdministrator.getLastPasswordResetDate(),systemAdministrator.getCity(),
        systemAdministrator.getCountry(),systemAdministrator.getPhoneNumber(),systemAdministrator.getWorkplace(),systemAdministrator.getCompanyName());
    }

    public SystemAdministratorResponseDTO(Integer id, String email, String password, String firstName, String lastName, 
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
