package com.isa2023team64.pharmacydeliverybe.dto;

import java.time.LocalTime;

import com.isa2023team64.pharmacydeliverybe.model.Company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyInfoResponseDTO extends CompanyInfoRequestDTO {

    private Integer id;
    private double averageRating;

    public CompanyInfoResponseDTO(Company company) {
        this(company.getId(), company.getName(), company.getAddress(), company.getCity(), company.getCountry(), company.getDescription(), company.getAverageRating(), company.getOpeningTime(), company.getClosingTime());
    }

    public CompanyInfoResponseDTO(Integer id, String name, String address, String city, String country, String description, double averageRating,
            LocalTime openingTime, LocalTime closingTime) {
        super(name, address, city, country, description, openingTime, closingTime);
        this.id = id;
        this.averageRating = averageRating;
    }

}
