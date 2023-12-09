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
            companyAdministrators.forEach(companyAdministrator -> companyAdministrator.setCompany(this));
        }
    }

    public void setName(String name) {
        this.name = name;
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

    public void setCountry(String country) {
        this.country = country;
    }

    public void setOpeningTime(LocalTime openingTime){
        this.openingTime = openingTime;
    }

    public void setClosingTime(LocalTime closingTime){
        this.closingTime = closingTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void setCompanyAdministrators(List<CompanyAdministrator> companyAdministrators) {
        this.companyAdministrators = companyAdministrators;
        if(companyAdministrators != null) {
            companyAdministrators.forEach(companyAdministrator -> companyAdministrator.setCompany(this));
        }
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
}
