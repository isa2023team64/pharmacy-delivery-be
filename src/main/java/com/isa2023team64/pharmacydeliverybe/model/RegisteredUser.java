package com.isa2023team64.pharmacydeliverybe.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegisteredUser extends User {

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

    public RegisteredUser(Integer id, String username, String email, String password, String firstName, String lastName, boolean active, @NotEmpty String city, @NotEmpty String country, @NotEmpty @Pattern (regexp = "\\+\\d{12}", message = "Phone number must start with '+' and be followed by 12 digits") 
            String phoneNumber, @NotEmpty String workplace, @NotEmpty String companyName) {
        super(id, email, password, firstName, lastName,active);
        this.city=city;
        this.country=country;
        this.companyName=companyName;
        this.workplace=workplace;
        this.phoneNumber=phoneNumber;
    }

}
