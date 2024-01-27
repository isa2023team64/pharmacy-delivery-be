package com.isa2023team64.pharmacydeliverybe.service;

import org.springframework.kafka.core.KafkaTemplate;

public interface MonthlyDeliveryService {

    public void checkDeliveries();

    public void startDeliveries();
    
}
