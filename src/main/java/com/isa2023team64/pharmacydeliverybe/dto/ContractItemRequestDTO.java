package com.isa2023team64.pharmacydeliverybe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContractItemRequestDTO {
    @JsonProperty("equipmentId")
    private int equipmentId;

    @JsonProperty("quantity")
    private int quantity;

}
