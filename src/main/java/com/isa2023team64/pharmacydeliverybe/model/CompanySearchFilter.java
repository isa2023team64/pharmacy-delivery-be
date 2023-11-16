package com.isa2023team64.pharmacydeliverybe.model;

public class CompanySearchFilter {
    private String name;
    private String country;
    private String city;
    private double minRating;
    private double maxRating;
    private String sortCriteria;

    public CompanySearchFilter(String name, String country, String city, double minRating, double maxRating) {
        super();
        this.name = name;
        this.country = country;
        this.city = city;
        this.minRating = minRating;
        this.maxRating = maxRating;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public double getMinRating() {
        return minRating;
    }

    public double getMaxRating() {
        return maxRating;
    }

    public String getSortCriteria() {
        return sortCriteria;
    }    
}
