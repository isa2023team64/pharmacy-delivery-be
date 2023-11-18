package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;

public class EquipmentRequestDTO {
    private String name;
    private String description;
    private String type;
    private double averageRating;


    public EquipmentRequestDTO(){
        super();
    }

    public EquipmentRequestDTO(Equipment equipment) {
        this(equipment.getName(), equipment.getDescription(), equipment.getType(), equipment.getAverageRating());
    }

    public EquipmentRequestDTO(String name, String description, String type, double averageRating) {
        super();
        this.name = name;
        this.description = description;
        this.type = type;
        this.averageRating = averageRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }

    public double getAverageRating(){
        return averageRating;
    }

    public void setAverageRating(double averageRating){
        this.averageRating = averageRating;
    }

}
