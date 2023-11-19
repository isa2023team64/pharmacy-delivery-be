package com.isa2023team64.pharmacydeliverybe.dto;

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

    public CompanyNoAdminDTO() {
        super();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
