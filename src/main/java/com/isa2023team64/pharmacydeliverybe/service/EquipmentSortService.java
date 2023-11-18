package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;

public interface EquipmentSortService {

    public List<Equipment> sort(List<Equipment> equipment, String criteria);
}