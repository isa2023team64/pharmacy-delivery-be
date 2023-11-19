package com.isa2023team64.pharmacydeliverybe.service;


import java.util.List;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;
import com.isa2023team64.pharmacydeliverybe.model.EquipmentSearchFilter;

public interface EquipmentFilterService {

    public List<Equipment> filter(List<Equipment> companies, EquipmentSearchFilter filter);
}