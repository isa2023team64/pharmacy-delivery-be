package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;

public class EquipmentResponseDTO extends EquipmentRequestDTO {
    private Integer id;

    public EquipmentResponseDTO() {
        super();
    }

    public EquipmentResponseDTO(Equipment equipment) {
        this(equipment.getId(), equipment.getName(), equipment.getDescription(), equipment.getType(), equipment.getAverageRating());
    }

    public EquipmentResponseDTO(Integer id, String name, String description, String type, double averageRating) {
        super(name, description, type, averageRating);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
