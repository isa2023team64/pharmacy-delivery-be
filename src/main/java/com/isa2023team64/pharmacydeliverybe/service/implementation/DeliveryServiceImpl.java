package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.Delivery;
import com.isa2023team64.pharmacydeliverybe.repository.DeliveryRepository;
import com.isa2023team64.pharmacydeliverybe.service.DeliveryService;
import com.isa2023team64.pharmacydeliverybe.util.enums.DeliveryStatus;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public Collection<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    @Override
    public Collection<Delivery> findUndelivered() {
        return deliveryRepository.findAll().stream().filter(d -> d.getStatus() != DeliveryStatus.DELIVERED).toList();
    }
    
}
