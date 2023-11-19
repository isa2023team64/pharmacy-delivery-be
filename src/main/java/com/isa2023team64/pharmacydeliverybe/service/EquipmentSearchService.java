package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import com.isa2023team64.pharmacydeliverybe.dto.EquipmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentSearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.model.Equipment;
import com.isa2023team64.pharmacydeliverybe.util.PagedResult;

public interface EquipmentSearchService {

    public PagedResult<EquipmentResponseDTO> search(EquipmentSearchFilterDTO filter);

    public List<Equipment> searchEntities(EquipmentSearchFilterDTO filter);
}