package com.isa2023team64.pharmacydeliverybe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class MockCompanyAdministrator extends User {

    @ManyToOne
    private Company company;

    public MockCompanyAdministrator() {
        super();
    }

    public MockCompanyAdministrator(Integer id, String username, String email, String password) {
        super(id, username, email, password);
    }

    public Company getCompany(){
        return this.company;
    }

    public void setCompany(Company company){
        this.company = company;
    }

}
