package com.isa2023team64.pharmacydeliverybe.dto;

public class CompanySearchFilterDTO {
    private String name;
    private String country;
    private String city;
    private String minRating;
    private String maxRating;
    private String sortCriteria;
    private int page;
    private int pageSize;

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

    public String getMinRating() {
        return minRating;
    }

    public void setMinRating(String minRating) {
        this.minRating = minRating;
    }

    public String getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(String maxRating) {
        this.maxRating = maxRating;
    }

    public String getSortCriteria() {
        return sortCriteria;
    }

    public void setSortCriteria(String sortCriteria) {
        this.sortCriteria = sortCriteria;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
