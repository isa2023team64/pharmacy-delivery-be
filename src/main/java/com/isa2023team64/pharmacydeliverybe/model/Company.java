package com.isa2023team64.pharmacydeliverybe.model;

import java.util.List;


import java.util.ArrayList;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity
@Getter
@NoArgsConstructor
public class Company extends GenericEntity{
 
    @Column(unique = true, nullable = false)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @NotEmpty
    private String address;

    @Column(nullable = false)
    @NotEmpty
    private String city;

    @Column(nullable = false)
    @NotEmpty
    private String country;

    @Column(nullable = false)
    private LocalTime openingTime;

    @Column(nullable = false)
    private LocalTime closingTime;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double averageRating;

    @Column(name = "image_url", nullable = false)
    @NotEmpty
    private String imageURL;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CompanyAdministrator> companyAdministrators;
    
    @ManyToMany( fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	@JoinTable(name = "company_uses_equipment", joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "id"))
    private List<Equipment> equipment;

    public Company(Integer id, String name, String address, String description, double averageRating, String imageURL){
        super(id);
        this.name = name;
        this.address = address;
        this.description = description;
        this.averageRating = averageRating;
        this.imageURL = imageURL;
        this.companyAdministrators = new ArrayList<CompanyAdministrator>();
    }   

    public Company(Integer id, String name, String address, String description, double averageRating, String imageURL, List<CompanyAdministrator> companyAdministrators) {
        super(id);
        this.name = name;
        this.address = address;
        this.description = description;
        this.averageRating = averageRating;
        this.imageURL = imageURL;
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

    public List<CompanyAdministrator> getCompanyAdministrators() {
        return companyAdministrators;
    }

    public void setCompanyAdministrators(List<CompanyAdministrator> companyAdministrators) {
        this.companyAdministrators = companyAdministrators;
        if(companyAdministrators != null) {
            companyAdministrators.forEach(companyAdministrator -> companyAdministrator.setCompanyEntity(this));
        }
    }
    
    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
