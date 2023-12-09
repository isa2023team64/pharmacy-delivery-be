package com.isa2023team64.pharmacydeliverybe.dto;

import java.time.LocalTime;

import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.util.WorkingHours;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInfoRequestDTO {

    private String name;
    private String address;
    private String city;
    private String country;
    private String description;
    private String openingTime;
    private String closingTime;

    public CompanyInfoRequestDTO(Company company) {
        this(company.getName(), company.getAddress(), company.getCity(), company.getCountry(), company.getDescription(), company.getOpeningTime(), company.getClosingTime());
    }

    public CompanyInfoRequestDTO(String name, String address, String city, String country, String description,
            LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.description = description;
        this.openingTime = WorkingHours.formatTime(openingTime);
        this.closingTime = WorkingHours.formatTime(closingTime);
    }

}
