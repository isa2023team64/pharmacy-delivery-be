package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.MockCompanyAdministrator;

public class MockCompanyAdministratorRequestDTO {
    private String username;
    private String email;
    private String password;

    public MockCompanyAdministratorRequestDTO() {
        super();
    }


    public MockCompanyAdministratorRequestDTO(MockCompanyAdministrator companyAdministrator){
        this(companyAdministrator.getUsername(), companyAdministrator.getEmail(), companyAdministrator.getPassword());
    }

    public MockCompanyAdministratorRequestDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
