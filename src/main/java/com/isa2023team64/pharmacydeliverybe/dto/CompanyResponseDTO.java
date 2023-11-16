package com.isa2023team64.pharmacydeliverybe.dto;

import java.time.LocalTime;
import java.util.List;

import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.model.MockCompanyAdministrator;

public class CompanyResponseDTO extends CompanyRequestDTO{
    private Integer id;

    public CompanyResponseDTO(){
        super();
    }

    public CompanyResponseDTO(Company company){
        this(company.getId(), company.getName(), company.getAddress(), company.getCity(), company.getCountry(), company.getOpeningTime(),
        company.getClosingTime(), company.getDescription(), company.getAverageRating(), 
        company.getCompanyAdministrators());
    }

    public CompanyResponseDTO(Integer id, String name, String address, String city, String country, LocalTime openingTime, LocalTime closingTime,
    String description, double averageRating, List<MockCompanyAdministrator> companyAdministrators) {
        super(name ,address, city, country, openingTime, closingTime, description, averageRating, companyAdministrators);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
