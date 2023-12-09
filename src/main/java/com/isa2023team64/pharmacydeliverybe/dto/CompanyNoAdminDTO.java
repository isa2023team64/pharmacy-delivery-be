package com.isa2023team64.pharmacydeliverybe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyNoAdminDTO {

    private Integer id;
    private String name;
    private String address;
    private String city;
    private String country;
    private String description;
    private double averageRating;
    private String imageURL;
    private String openingTime;
    private String closingTime;

}
