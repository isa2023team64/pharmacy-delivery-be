package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentRequestDTO {
    
    private String name;
    private String description;
    private String type;
    private double averageRating;

    public EquipmentRequestDTO(Equipment equipment) {
        this(equipment.getName(), equipment.getDescription(), equipment.getType(), equipment.getAverageRating());
    }

}
