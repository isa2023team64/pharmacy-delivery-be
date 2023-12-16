package com.isa2023team64.pharmacydeliverybe.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isa2023team64.pharmacydeliverybe.dto.EquipmentRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.Equipment;

@Component
public class EquipmentDTOMapper {
    
    private static ModelMapper mapper;

    @Autowired
    public EquipmentDTOMapper(ModelMapper mapper) {
        super();
        EquipmentDTOMapper.mapper = mapper;
    }

    public static Equipment fromRequestDTO(EquipmentRequestDTO dto) {
        return mapper.map(dto, Equipment.class);
    }
    
    public static EquipmentResponseDTO toResponseDTO(Equipment equipment) {
        return mapper.map(equipment, EquipmentResponseDTO.class);
    }

}
