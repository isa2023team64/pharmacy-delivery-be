package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.MockCompanyAdministrator;

public class MockCompanyAdministratorResponseDTO extends MockCompanyAdministratorRequestDTO{
    private Integer id;

    public MockCompanyAdministratorResponseDTO() {
        super();
    }


    public MockCompanyAdministratorResponseDTO(MockCompanyAdministrator companyAdministrator){
        this(companyAdministrator.getId() ,companyAdministrator.getUsername(), companyAdministrator.getEmail(), companyAdministrator.getPassword());
    }

    public MockCompanyAdministratorResponseDTO(Integer id, String username, String email, String password) {
        super(username, email, password);
        this.id = id;        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
