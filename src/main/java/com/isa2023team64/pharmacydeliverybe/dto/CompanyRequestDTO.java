package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;
import com.isa2023team64.pharmacydeliverybe.util.WorkingHours;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestDTO {
    
    private String name;
    private String address;
    private String city;
    private String country;
    private String description;
    private String imageURL;

    private String openingTime;
    private String closingTime;

    private List<CompanyAdministratorRequestDTO> companyAdministrators;

    public CompanyRequestDTO(Company company){
        this(company.getName(), company.getAddress(), company.getCity(), company.getCountry(), company.getImageURL(), company.getOpeningTime(),
        company.getClosingTime(), company.getDescription(), company.getCompanyAdministrators());
    }

    public CompanyRequestDTO(String name, String address, String city, String country, String imageURL, LocalTime openingTime, LocalTime closingTime,
    String description, List<CompanyAdministrator> companyAdministrators) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.openingTime = WorkingHours.formatTime(openingTime);
        this.closingTime = WorkingHours.formatTime(closingTime);
        this.description = description;
        this.imageURL = imageURL;
        if (companyAdministrators != null) {
            this.companyAdministrators = companyAdministrators
                    .stream()
                    .map(CompanyAdministratorRequestDTO::new)
                    .collect(Collectors.toList());
        }
    }

}
