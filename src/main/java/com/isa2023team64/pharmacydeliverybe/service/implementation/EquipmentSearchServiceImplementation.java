package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.dto.EquipmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentSearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.model.Equipment;
import com.isa2023team64.pharmacydeliverybe.model.EquipmentSearchFilter;
import com.isa2023team64.pharmacydeliverybe.repository.EquipmentRepository;
import com.isa2023team64.pharmacydeliverybe.service.CompanyService;
import com.isa2023team64.pharmacydeliverybe.service.EquipmentFilterService;
import com.isa2023team64.pharmacydeliverybe.service.EquipmentSearchService;
import com.isa2023team64.pharmacydeliverybe.service.EquipmentSortService;
import com.isa2023team64.pharmacydeliverybe.util.PagedResult;
import com.isa2023team64.pharmacydeliverybe.util.converters.PagedResultConverter;
import com.isa2023team64.pharmacydeliverybe.model.Company;

@Service
public class EquipmentSearchServiceImplementation implements EquipmentSearchService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentFilterService filterService;

    @Autowired
    private EquipmentSortService sortService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PagedResultConverter pagedResultConverter;

    @Autowired
    private CompanyService companyService;

    public PagedResult<EquipmentResponseDTO> search(EquipmentSearchFilterDTO filter) {
        List<Equipment> equipments = equipmentRepository.findAll();

        EquipmentSearchFilter searchFilter = new EquipmentSearchFilter(
            filter.getName(), filter.getDescription(), filter.getType(), filter.getMinRating(), filter.getMaxRating());

        equipments = filterService.filter(equipments, searchFilter);
        equipments = sortService.sort(equipments, filter.getSortCriteria());

        List<EquipmentResponseDTO> equipmentDTOs = equipments.stream()
                .map(equipment -> modelMapper.map(equipment, EquipmentResponseDTO.class))
                .collect(Collectors.toList());

        PagedResult<EquipmentResponseDTO> equipmentPage = pagedResultConverter.convertToPagedResult(
            equipmentDTOs, filter.getPage(), filter.getPageSize());

        return equipmentPage;
    }

    public PagedResult<EquipmentResponseDTO> searchWithCompanyAdministrator(Integer companyAdministratorId, EquipmentSearchFilterDTO filter) {
        // List<Equipment> equipments = equipmentRepository.findAll();

        Company company = companyService.findCompanyByAdministratorId(companyAdministratorId);
        
        List<Equipment> equipments = equipmentRepository.findByCompanyId(company.getId());

        // EquipmentSearchFilter searchFilter = new EquipmentSearchFilter(
        //     filter.getName(), filter.getDescription(), filter.getType(), filter.getMinRating(), filter.getMaxRating());

        // equipments = filterService.filter(equipments, searchFilter);
        // equipments = sortService.sort(equipments, filter.getSortCriteria());

        List<EquipmentResponseDTO> equipmentDTOs = equipments.stream()
                .map(equipment -> modelMapper.map(equipment, EquipmentResponseDTO.class))
                .collect(Collectors.toList());

        PagedResult<EquipmentResponseDTO> equipmentPage = pagedResultConverter.convertToPagedResult(
            equipmentDTOs, filter.getPage(), filter.getPageSize());

        return equipmentPage;
    }

    public List<Equipment> searchEntities(EquipmentSearchFilterDTO filter){
        List<Equipment> equipments = equipmentRepository.findAll();

        EquipmentSearchFilter searchFilter = new EquipmentSearchFilter(
            filter.getName(), filter.getDescription(), filter.getType(), filter.getMinRating(), filter.getMaxRating());
    
        equipments = filterService.filter(equipments, searchFilter);
        equipments = sortService.sort(equipments, filter.getSortCriteria());

        return equipments;
    }
}
