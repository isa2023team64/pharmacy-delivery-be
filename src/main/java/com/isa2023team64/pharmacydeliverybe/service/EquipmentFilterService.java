package com.isa2023team64.pharmacydeliverybe.service;

import java.util.ArrayList;

import com.isa2023team64.pharmacydeliverybe.model.EquipmentSearchFilter;

public interface EquipmentFilterService {

    public void filter(ArrayList<String> companies, EquipmentSearchFilter filter);
}