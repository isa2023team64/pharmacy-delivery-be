package com.isa2023team64.pharmacydeliverybe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractItemRequestDTO {
    @JsonProperty("equipmentId")
    private int equipmentId;

    @JsonProperty("quantity")
    private int quantity;

}
