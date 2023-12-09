package com.isa2023team64.pharmacydeliverybe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanySearchFilterDTO {

    private String name;
    private String country;
    private String city;
    private String minRating;
    private String maxRating;
    private String sortCriteria;
    private int page;
    private int pageSize;

}
