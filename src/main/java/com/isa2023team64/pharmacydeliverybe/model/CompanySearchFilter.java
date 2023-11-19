package com.isa2023team64.pharmacydeliverybe.model;

public class CompanySearchFilter {
    private String name;
    private String country;
    private String city;
    private double minRating;
    private double maxRating;
    private String sortCriteria;

    public CompanySearchFilter(String name, String country, String city, String minRating, String maxRating) {
        super();
        this.name = name != null ? name : "";
        this.country = country != null ? country : "";
        this.city = city != null ? city : "";

        try {
            this.minRating = Double.parseDouble(minRating);
            this.minRating = this.minRating < 0 ? 0 : this.minRating;
        } catch (Exception e) {
            this.minRating = 0;
        }

        try {
            this.maxRating = Double.parseDouble(maxRating);
            this.maxRating = this.maxRating < 0 ? 0 : this.maxRating;
        } catch (Exception e) {
            this.maxRating = 5;
        }
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
