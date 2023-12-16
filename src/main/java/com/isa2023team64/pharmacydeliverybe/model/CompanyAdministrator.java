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

    @Column
    @NotEmpty
    private String workplace;

    @Column
    @NotEmpty
    private String companyName;

    @Column
    private boolean firstLogin;

    public CompanyAdministrator(Integer id, String username, String email, String password, String firstName, String lastName, boolean active, @NotEmpty String city, @NotEmpty String country, @NotEmpty @Pattern (regexp = "\\+\\d{12}", message = "Phone number must start with '+' and be followed by 12 digits") 
            String phoneNumber, @NotEmpty String workplace, @NotEmpty String companyName, boolean firstLogin) {
        super(id, email, password, firstName, lastName,active);
        this.city=city;
        this.country=country;
        this.companyName=companyName;
        this.workplace=workplace;
        this.phoneNumber=phoneNumber;
        this.firstLogin = firstLogin;
    }

    public void setCompanyEntity(Company company){
        this.company = company;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
