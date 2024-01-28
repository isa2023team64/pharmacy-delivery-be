package com.isa2023team64.pharmacydeliverybe.service;

import java.util.Collection;

import com.isa2023team64.pharmacydeliverybe.model.Delivery;

public interface DeliveryService {
    
    public Collection<Delivery> findAll();

    public Collection<Delivery> findUndelivered();

    public void finishDelivery(int deliveryId);

}
