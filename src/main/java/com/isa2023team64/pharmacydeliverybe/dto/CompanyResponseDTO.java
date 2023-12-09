package com.isa2023team64.pharmacydeliverybe.dto;

import java.time.LocalTime;
import java.util.List;

import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyResponseDTO extends CompanyRequestDTO {

    private Integer id;
    private double averageRating;

    public CompanyResponseDTO(Company company){
        this(company.getId(), company.getName(), company.getAddress(), company.getCity(), company.getCountry(), company.getImageURL(), company.getOpeningTime(),
        company.getClosingTime(), company.getDescription(), company.getAverageRating(), 
        company.getCompanyAdministrators());
    }

    public CompanyResponseDTO(Integer id, String name, String address, String city, String country, String imageURL, LocalTime openingTime, LocalTime closingTime,
    String description, double averageRating, List<CompanyAdministrator> companyAdministrators) {
        super(name ,address, city, country, imageURL, openingTime, closingTime, description, companyAdministrators);
        this.id = id;
        this.averageRating = averageRating;
    }

}
