package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.dto.CompanyNoAdminDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanySearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentSearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.repository.CompanyRepository;
import com.isa2023team64.pharmacydeliverybe.util.PagedResult;
import com.isa2023team64.pharmacydeliverybe.util.converters.PagedResultConverter;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PagedResultConverter pagedResultConverter;

    public Company findById(Integer id){
        return companyRepository.findById(id).orElse(null);
    }

    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    public Company register(Company company) {
        return companyRepository.save(company);
    }

    public Company update(Company company) {
        return companyRepository.save(company);
    }

    public Company findOneWithEquipment(Integer companyId) {
        return companyRepository.findOneWithEquipment(companyId);
    }

    public PagedResult<CompanyNoAdminDTO> findCompaniesByEquipmentIds(List<Integer> equipmentIds, EquipmentSearchFilterDTO filter) {
        List<Company> companies = companyRepository.findCompaniesByEquipmentIds(equipmentIds);
        
        List<CompanyNoAdminDTO> companyDTOs = companies.stream()
        .map(company -> modelMapper.map(company, CompanyNoAdminDTO.class))
        .collect(Collectors.toList());

        PagedResult<CompanyNoAdminDTO> companyPage = pagedResultConverter.convertToPagedResult(
        companyDTOs, filter.getPage(), filter.getPageSize());

        return companyPage;
    }

    public PagedResult<CompanyNoAdminDTO> findCompaniesByEquipmentId(Integer equipmentId, CompanySearchFilterDTO filter) {
        List<Company> companies = companyRepository.findCompaniesWithSingleEquipmentById(equipmentId);

        List<CompanyNoAdminDTO> companyDTOs = companies.stream()
        .map(company -> modelMapper.map(company, CompanyNoAdminDTO.class))
        .collect(Collectors.toList());

        PagedResult<CompanyNoAdminDTO> companyPage = pagedResultConverter.convertToPagedResult(
        companyDTOs, filter.getPage(), filter.getPageSize());

        return companyPage;
    }
}
