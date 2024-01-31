package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;
import com.isa2023team64.pharmacydeliverybe.model.Reservation;
import com.isa2023team64.pharmacydeliverybe.repository.EquipmentRepository;
import com.isa2023team64.pharmacydeliverybe.repository.ReservationRepository;
import com.isa2023team64.pharmacydeliverybe.service.EquipmentService;



@Service
// @Transactional(readOnly = true)
public class EquipmentServiceImplementation implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment findById(Integer id) {
        return equipmentRepository.findById(id).orElseThrow();
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }
    
    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Equipment update(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment add(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    // @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void delete(Equipment equipment) {
        var reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {
            if (reservation.getOrderItems().contains(equipment)) {
                return;
            }
        }
        equipmentRepository.delete(equipment);
    }
}
