package com.isa2023team64.pharmacydeliverybe.dto;

public class EquipmentSearchFilterDTO {
    
    private String name;
    private String type;
    private String description;
    private double minRating;
    private double maxRating;
    private String sortCriteria;

    public EquipmentSearchFilterDTO(){
        super();
    }

    
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
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

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMinRating(double minRating) {
        this.minRating = minRating;
    }

    public void setMaxRating(double maxRating) {
        this.maxRating = maxRating;
    }

    public void setSortCriteria(String sortCriteria) {
        this.sortCriteria = sortCriteria;
    }


}
