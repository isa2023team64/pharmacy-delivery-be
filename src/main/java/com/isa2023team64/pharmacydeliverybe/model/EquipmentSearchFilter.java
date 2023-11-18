package com.isa2023team64.pharmacydeliverybe.model;

public class EquipmentSearchFilter {

    private String name;
    private String type;
    private String description;
    private double minRating;
    private double maxRating;
    private String sortCriteria;

    public EquipmentSearchFilter(String name, String description, String type, String minRating, String maxRating) {
        super();
        this.name = name != null ? name : "";
        this.type = type != null ? type : "";
        this.description = description != null ? description : "";

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
