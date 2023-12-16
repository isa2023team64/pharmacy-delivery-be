package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentResponseDTO extends EquipmentRequestDTO {

    private Integer id;

    public EquipmentResponseDTO(Equipment equipment) {
        this(equipment.getId(), equipment.getName(), equipment.getDescription(), equipment.getType(), equipment.getStockCount(), equipment.getCompany().getId());
    }

    public EquipmentResponseDTO(Integer id, String name, String description, String type, Integer stockCount, Integer companyId) {
        super(name, description, type, stockCount, companyId);
        this.id = id;
    }

}
