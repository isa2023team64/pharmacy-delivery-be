package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;

public interface EquipmentService {
    
    public List<Equipment> findAll();

    public Equipment findById(Integer id);

    public Equipment save(Equipment equipment);

    public Equipment update(Equipment equipment);
}
