package com.isa2023team64.pharmacydeliverybe.dto;

import java.time.LocalTime;
import java.util.List;

import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;

public class CompanyResponseDTO extends CompanyRequestDTO{
    private Integer id;
    private double averageRating;

    public CompanyResponseDTO(){
        super();
    }

    public CompanyResponseDTO(Company company){
        this(company.getId(), company.getName(), company.getAddress(), company.getCity(), company.getCountry(), company.getOpeningTime(),
        company.getClosingTime(), company.getDescription(), company.getAverageRating(), 
        company.getCompanyAdministrators());
    }

    public CompanyResponseDTO(Integer id, String name, String address, String city, String country, LocalTime openingTime, LocalTime closingTime,
    String description, double averageRating, List<CompanyAdministrator> companyAdministrators) {
        super(name ,address, city, country, openingTime, closingTime, description, companyAdministrators);
        this.id = id;
        this.averageRating = averageRating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
