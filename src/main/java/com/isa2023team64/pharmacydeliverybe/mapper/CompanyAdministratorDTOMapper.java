package com.isa2023team64.pharmacydeliverybe.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isa2023team64.pharmacydeliverybe.dto.CompanyAdministratorRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanyAdministratorResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;

@Component
public class CompanyAdministratorDTOMapper {
    
    private static ModelMapper mapper;

    @Autowired
    public CompanyAdministratorDTOMapper(ModelMapper mapper) {
        super();
        CompanyAdministratorDTOMapper.mapper = mapper;
    }

    public static CompanyAdministrator fromRequestDTO(CompanyAdministratorRequestDTO dto) {
        return mapper.map(dto, CompanyAdministrator.class);
    }

    public static CompanyAdministratorResponseDTO toResponseDTO(CompanyAdministrator companyAdministrator) {
        var companyAdministratorResponseDTO = new CompanyAdministratorResponseDTO(companyAdministrator);
        return companyAdministratorResponseDTO;
    }
    
}
