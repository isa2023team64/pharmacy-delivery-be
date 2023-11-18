package com.isa2023team64.pharmacydeliverybe.model;

import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class CompanyAdministrator extends User {

    @ManyToOne
    private Company company;

    public CompanyAdministrator() {
        super();
    }

    public CompanyAdministrator(Integer id, String email, String password, String firstName, String lastName,
    boolean active, Timestamp lastPasswordResetDate, String city, String country, String phoneNumber, String workplace, String companyName) {
        super(id, email, password, firstName, lastName, active, city, country, phoneNumber, workplace, companyName);
    }

    public Company getCompanyEntity(){
        return this.company;
    }

    public void setCompanyEntity(Company company){
        this.company = company;
    }

}
