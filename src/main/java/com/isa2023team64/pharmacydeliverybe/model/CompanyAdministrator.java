package com.isa2023team64.pharmacydeliverybe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CompanyAdministrator extends User {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column
    @NotEmpty
    private String city;

    @Column
    @NotEmpty
    private String country;

    @Column
    @NotEmpty
    @Pattern(regexp = "\\+\\d{12}", message = "Phone number must start with '+' and be followed by 12 digits")
    private String phoneNumber;



    public CompanyAdministrator(Integer id, String username, String email, String password, String firstName, String lastName, boolean active, @NotEmpty String city, @NotEmpty String country, @NotEmpty @Pattern (regexp = "\\+\\d{12}", message = "Phone number must start with '+' and be followed by 12 digits") 
            String phoneNumber) {
        super(id, email, password, firstName, lastName,active);
        this.city=city;
        this.country=country;
        this.phoneNumber=phoneNumber;
    }

    public Company getCompanyEntity(){
        return this.company;
    }

    public void setCompanyEntity(Company company){
        this.company = company;
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



}
