package com.isa2023team64.pharmacydeliverybe.dto;

public class CompanySearchFilterDTO {
    private String name;
    private String country;
    private String city;
    private double minRating;
    private double maxRating;
    private String sortCriteria;

    public CompanySearchFilterDTO() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getMinRating() {
        return minRating;
    }

    public void setMinRating(double minRating) {
        this.minRating = minRating;
    }

    public double getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(double maxRating) {
        this.maxRating = maxRating;
    }

    public String getSortCriteria() {
        return sortCriteria;
    }

    public void setSortCriteria(String sortCriteria) {
        this.sortCriteria = sortCriteria;
    }
}
