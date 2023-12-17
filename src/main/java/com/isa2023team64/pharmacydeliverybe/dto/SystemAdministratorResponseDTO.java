package com.isa2023team64.pharmacydeliverybe.dto;

import java.sql.Timestamp;

import com.isa2023team64.pharmacydeliverybe.model.SystemAdministrator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SystemAdministratorResponseDTO extends SystemAdministratorRequestDTO{

    private Integer id;

    public SystemAdministratorResponseDTO(SystemAdministrator systemAdministrator){
        this(systemAdministrator.getId(), systemAdministrator.getEmail(), systemAdministrator.getPassword(), systemAdministrator.getFirstName(), 
        systemAdministrator.getLastName(),systemAdministrator.isActive(),systemAdministrator.getLastPasswordResetDate(),systemAdministrator.getCity(),
        systemAdministrator.getCountry(),systemAdministrator.getPhoneNumber(), systemAdministrator.getFirstLogged());
    }

    public SystemAdministratorResponseDTO(Integer id, String email, String password, String firstName, String lastName, 
    Boolean active, Timestamp lastPasswordResetDate, String city, String country,String phoneNumber, boolean firstLogged) {
        
        super(email, password, firstName, lastName, city, country, phoneNumber, firstLogged);
        this.id = id;        
    }
    
}
