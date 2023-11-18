package com.isa2023team64.pharmacydeliverybe.dto;

public class EquipmentSearchFilterDTO {
    
    private String name;
    private String type;
    private String description;
    private String minRating;
    private String maxRating;
    private String sortCriteria;
    private int page;
    private int pageSize;


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

    public String getMinRating() {
        return minRating;
    }

    public String getMaxRating() {
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

    public void setMinRating(String minRating) {
        this.minRating = minRating;
    }

    public void setMaxRating(String maxRating) {
        this.maxRating = maxRating;
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
