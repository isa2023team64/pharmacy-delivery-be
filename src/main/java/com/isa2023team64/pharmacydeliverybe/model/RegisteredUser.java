package com.isa2023team64.pharmacydeliverybe.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
public class RegisteredUser extends User {

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "country")
    @NotEmpty
    private String country;

    @Column(name = "phone_number")
    @NotEmpty
    @Pattern(regexp = "\\+\\d{12}", message = "Phone number must start with '+' and be followed by 12 digits")
    private String phoneNumber;

    @Column(name = "workplace")
    @NotEmpty
    private String workplace;

    @Column(name = "company_name")
    @NotEmpty
    private String companyName;

    public RegisteredUser() {
        super();
    }

    public RegisteredUser(Integer id, String username, String email, String password, String firstName, String lastName, boolean active, @NotEmpty String city, @NotEmpty String country, @NotEmpty @Pattern (regexp = "\\+\\d{12}", message = "Phone number must start with '+' and be followed by 12 digits") 
            String phoneNumber, @NotEmpty String workplace, @NotEmpty String companyName) {
        super(id, email, password, firstName, lastName,active);
        this.city=city;
        this.country=country;
        this.companyName=companyName;
        this.workplace=workplace;
        this.phoneNumber=phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
