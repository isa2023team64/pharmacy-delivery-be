package com.isa2023team64.pharmacydeliverybe.service;

import com.isa2023team64.pharmacydeliverybe.dto.EquipmentRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentSearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.util.PagedResult;

public interface EquipmentSearchService {

    public PagedResult<EquipmentRequestDTO> search(EquipmentSearchFilterDTO filter);
}