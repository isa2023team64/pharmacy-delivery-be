package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.dto.CompanyNoAdminDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanySearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.model.CompanySearchFilter;
import com.isa2023team64.pharmacydeliverybe.repository.CompanyRepository;
import com.isa2023team64.pharmacydeliverybe.service.CompanyFilterService;
import com.isa2023team64.pharmacydeliverybe.service.CompanySearchService;
import com.isa2023team64.pharmacydeliverybe.service.CompanySortService;
import com.isa2023team64.pharmacydeliverybe.util.PagedResult;
import com.isa2023team64.pharmacydeliverybe.util.converters.PagedResultConverter;

@Service
public class CompanySearchServiceImplementation implements CompanySearchService {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyFilterService filterService;

    @Autowired
    private CompanySortService sortService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PagedResultConverter pagedResultConverter;

    public PagedResult<CompanyNoAdminDTO> search(CompanySearchFilterDTO filter) {
        List<Company> companies = companyRepository.findAll();

        CompanySearchFilter searchFilter = new CompanySearchFilter(filter.getName(), filter.getCountry(), filter.getCity(), filter.getMinRating(), filter.getMaxRating());
        companies = filterService.filter(companies, searchFilter);

        companies = sortService.sort(companies, filter.getSortCriteria());
        
        List<CompanyNoAdminDTO> companiesDTO = companies.stream()
                .map(company -> modelMapper.map(company, CompanyNoAdminDTO.class))
                .collect(Collectors.toList());
        PagedResult<CompanyNoAdminDTO> companiesPage = pagedResultConverter.convertToPagedResult(companiesDTO, filter.getPage(), filter.getPageSize());
        
        return companiesPage;
    }
}
