package com.isa2023team64.pharmacydeliverybe.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Equipment extends GenericEntity {
    
    @Column(unique = true, nullable = false)
    @NotEmpty
    private String name;

    @Column
    @NotNull
    private String description;

    @Column
    @NotNull
    private String type;

    @Column
    @NotNull
    private double averageRating;

	@ManyToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	@JoinTable(name = "company_uses_equipment", joinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"))
    private List<Company> companies;

    public Equipment() {
        super();
    }

    public Equipment(Integer id, String name, String description, String type, double averageRating) {
        super(id);
        this.name = name;
        this.description = description;
        this.type = type;
        this.averageRating = averageRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }

    public double getAverageRating(){
        return averageRating;
    }

    public void setAverageRating(double averageRating){
        this.averageRating = averageRating;

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;

    }

}
