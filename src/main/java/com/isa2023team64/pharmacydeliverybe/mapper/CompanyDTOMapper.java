package com.isa2023team64.pharmacydeliverybe.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.isa2023team64.pharmacydeliverybe.dto.CompanyRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanyResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.Company;

public class CompanyDTOMapper {

    private static ModelMapper mapper;

    @Autowired
    public CompanyDTOMapper(ModelMapper mapper) {
        super();
        CompanyDTOMapper.mapper = mapper;
    }

    public static Company fromRequestDTO(CompanyRequestDTO dto) {
        return mapper.map(dto, Company.class);
    }

    public static CompanyResponseDTO toResponseDTO(CompanyRequestDTO company) {
        return mapper.map(company, CompanyResponseDTO.class);
    }
}
