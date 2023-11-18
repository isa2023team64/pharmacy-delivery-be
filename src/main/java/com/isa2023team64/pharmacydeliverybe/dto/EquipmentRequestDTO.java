package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;

public class EquipmentRequestDTO {
    private String name;
    private String description;

    public EquipmentRequestDTO() {
        super();
    }

    public EquipmentRequestDTO(Equipment equipment) {
        this(equipment.getName(), equipment.getDescription());
    }

    public EquipmentRequestDTO(String name, String description) {
        super();
        this.name = name;
        this.description = description;
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

}
