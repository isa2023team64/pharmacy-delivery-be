package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.dto.EquipmentRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentSearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.model.Equipment;
import com.isa2023team64.pharmacydeliverybe.model.EquipmentSearchFilter;
import com.isa2023team64.pharmacydeliverybe.repository.EquipmentRepository;
import com.isa2023team64.pharmacydeliverybe.service.EquipmentFilterService;
import com.isa2023team64.pharmacydeliverybe.service.EquipmentSearchService;
import com.isa2023team64.pharmacydeliverybe.service.EquipmentSortService;
import com.isa2023team64.pharmacydeliverybe.util.PagedResult;
import com.isa2023team64.pharmacydeliverybe.util.converters.PagedResultConverter;

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

    public PagedResult<EquipmentRequestDTO> search(EquipmentSearchFilterDTO filter) {
        List<Equipment> equipments = equipmentRepository.findAll();

        EquipmentSearchFilter searchFilter = new EquipmentSearchFilter(
            filter.getName(), filter.getDescription(), filter.getType(), filter.getMinRating(), filter.getMaxRating());

        equipments = filterService.filter(equipments, searchFilter);
        equipments = sortService.sort(equipments, filter.getSortCriteria());

        List<EquipmentRequestDTO> equipmentDTOs = equipments.stream()
                .map(equipment -> modelMapper.map(equipment, EquipmentRequestDTO.class))
                .collect(Collectors.toList());

        PagedResult<EquipmentRequestDTO> equipmentPage = pagedResultConverter.convertToPagedResult(
            equipmentDTOs, filter.getPage(), filter.getPageSize());

        return equipmentPage;
    }
}
