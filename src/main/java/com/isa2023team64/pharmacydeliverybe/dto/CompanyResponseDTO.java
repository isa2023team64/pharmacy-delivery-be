package com.isa2023team64.pharmacydeliverybe.dto;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;
import com.isa2023team64.pharmacydeliverybe.util.WorkingHours;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyResponseDTO implements Serializable {

    private Integer id;
    private String name;
    private String address;
    private String city;
    private String country;
    private String description;
    private String imageURL;
    private String openingTime;
    private String closingTime;
    private double averageRating;

    private List<CompanyAdministratorResponseDTO> companyAdministrators;

    public CompanyResponseDTO(Company company){
        this(company.getId(), company.getName(), company.getAddress(), company.getCity(), company.getCountry(), company.getImageURL(), company.getOpeningTime(),
        company.getClosingTime(), company.getDescription(), company.getAverageRating(), 
        company.getCompanyAdministrators());
    }

    public CompanyResponseDTO(Integer id, String name, String address, String city, String country, String imageURL, LocalTime openingTime, LocalTime closingTime,
    String description, double averageRating, List<CompanyAdministrator> companyAdministrators) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.openingTime = WorkingHours.formatTime(openingTime);
        this.closingTime = WorkingHours.formatTime(closingTime);
        this.description = description;
        this.imageURL = imageURL;
        this.id = id;
        this.averageRating = averageRating;
        if (companyAdministrators != null) {
            this.companyAdministrators = companyAdministrators
                    .stream()
                    .map(CompanyAdministratorResponseDTO::new)
                    .collect(Collectors.toList());
        }
    }

}
