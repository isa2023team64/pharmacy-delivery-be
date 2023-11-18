package com.isa2023team64.pharmacydeliverybe.model;

import java.util.List;


import java.util.ArrayList;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;



@Entity
@Table(name="app_company")
public class Company extends GenericEntity{
 
    @Column(unique = true, nullable = false)
    @NotEmpty
    private String name;

    @Column(unique = true, nullable = false)
    @NotEmpty
    private String address;

    @Column(unique = true, nullable = false)
    @NotEmpty
    private String city;

    @Column(unique = true, nullable = false)
    @NotEmpty
    private String country;

    @Column(unique = true, nullable = false)
    private LocalTime openingTime;

    @Column(unique = true, nullable = false)
    private LocalTime closingTime;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double averageRating;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MockCompanyAdministrator> companyAdministrators;


    public Company(){
        super();
    }

    public Company(Integer id, String name, String address, String description, double averageRating){
        super(id);
        this.name = name;
        this.address = address;
        this.description = description;
        this.averageRating = averageRating;
        this.companyAdministrators = new ArrayList<MockCompanyAdministrator>();
    }   

    public Company(Integer id, String name, String address, String description, double averageRating, List<MockCompanyAdministrator> companyAdministrators) {
        super(id);
        this.name = name;
        this.address = address;
        this.description = description;
        this.averageRating = averageRating;
        this.companyAdministrators = companyAdministrators;
        if (companyAdministrators != null) {
            companyAdministrators.forEach(companyAdministrator -> companyAdministrator.setCompanyEntity(this));
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public LocalTime getOpeningTime(){
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime){
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime(){
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime){
        this.closingTime = closingTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<MockCompanyAdministrator> getCompanyAdministrators() {
        return companyAdministrators;
    }

    public void setCompanyAdministrators(List<MockCompanyAdministrator> companyAdministrators) {
        this.companyAdministrators = companyAdministrators;
        if(companyAdministrators != null) {
            companyAdministrators.forEach(companyAdministrator -> companyAdministrator.setCompanyEntity(this));
        }
    }

}
