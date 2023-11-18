package com.isa2023team64.pharmacydeliverybe.dto;

import java.time.LocalTime;

import com.isa2023team64.pharmacydeliverybe.model.Company;

public class CompanyInfoRequestDTO {
    private String name;
    private String address;
    private String city;
    private String country;
    private String description;
    private String openingTime;
    private String closingTime;

    public CompanyInfoRequestDTO() {
        super();
    }

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
        this.openingTime = openingTime.getHour()+":"+openingTime.getMinute()+":"+openingTime.getSecond();
        this.closingTime = closingTime.getHour()+":"+closingTime.getMinute()+":"+openingTime.getSecond();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }
}
