package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;
import com.isa2023team64.pharmacydeliverybe.repository.AppointmentRepository;
import com.isa2023team64.pharmacydeliverybe.repository.EquipmentRepository;
import com.isa2023team64.pharmacydeliverybe.service.EquipmentService;

@Service
public class EquipmentServiceImplementation implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment findById(Integer id) {
        return equipmentRepository.findById(id).orElseThrow();
    }

    @Override
    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }
    
    @Override
    public Equipment update(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment add(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public void delete(Equipment equipment) {
        equipmentRepository.delete(equipment);
    }
}
