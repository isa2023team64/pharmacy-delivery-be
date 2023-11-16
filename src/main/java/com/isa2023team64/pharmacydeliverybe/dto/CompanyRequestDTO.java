package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.model.MockCompanyAdministrator;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalTime;

public class CompanyRequestDTO {
    private String name;
    private String address;
    private String city;
    private String country;
    private String description;
    private double averageRating;

    private String openingTime;
    private String closingTime;

    private List<MockCompanyAdministratorRequestDTO> companyAdministrators;

    public CompanyRequestDTO() {
        super();
    }

    public CompanyRequestDTO(Company company){
        this(company.getName(), company.getAddress(), company.getCity(), company.getCountry(), company.getOpeningTime(),
        company.getClosingTime(), company.getDescription(), company.getAverageRating(), 
        company.getCompanyAdministrators());
    }

    public CompanyRequestDTO(String name, String address, String city, String country, LocalTime openingTime, LocalTime closingTime,
    String description, double averageRating, List<MockCompanyAdministrator> companyAdministrators) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.openingTime = openingTime.getHour()+":"+openingTime.getMinute()+":"+openingTime.getSecond();
        this.closingTime = closingTime.getHour()+":"+closingTime.getMinute()+":"+openingTime.getSecond();
        this.description = description;
        this.averageRating = averageRating;
        if (companyAdministrators != null) {
            this.companyAdministrators = companyAdministrators
                    .stream()
                    .map(MockCompanyAdministratorRequestDTO::new)
                    .collect(Collectors.toList());
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOpeningTime(){
        return openingTime;
    }

    public void setOpeningTime(String openingTime){
        this.openingTime = openingTime;
    }

    public String getClosingTime(){
        return closingTime;
    }

    public void setClosingTime(String closingTime){
        this.closingTime = closingTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<MockCompanyAdministratorRequestDTO> getCompanyAdministrators() {
        return companyAdministrators;
    }

    public void setCompanyAdministrators(List<MockCompanyAdministratorRequestDTO> companyAdministrators) {
        this.companyAdministrators = companyAdministrators;
    }
}
