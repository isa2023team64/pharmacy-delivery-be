package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.Delivery;
import com.isa2023team64.pharmacydeliverybe.util.enums.DeliveryStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponseDTO {
 
    private int id;
    private String hospitalName;
    private String deliveryDate;
    private DeliveryStatus status;

    public DeliveryResponseDTO(Delivery delivery) {
        id = delivery.getId();
        hospitalName = delivery.getHospital().getName();
        deliveryDate = delivery.getDeliveryDate().toString();
        status = delivery.getStatus();
    }
    
}
