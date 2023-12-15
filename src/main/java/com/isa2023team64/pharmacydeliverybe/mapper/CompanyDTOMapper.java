package com.isa2023team64.pharmacydeliverybe.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.isa2023team64.pharmacydeliverybe.dto.CompanyInfoResponseDTO;
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

    public static CompanyResponseDTO toResponseDTO(Company company) {
        return mapper.map(company, CompanyResponseDTO.class);
    }

    public static CompanyInfoResponseDTO toInfoResponseDTO(Company company) {
        return mapper.map(company, CompanyInfoResponseDTO.class);
    }

}
