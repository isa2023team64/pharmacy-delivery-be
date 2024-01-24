package com.isa2023team64.pharmacydeliverybe.dto;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContractRequestDTO {

    @JsonProperty("hospitalId")
    private int hospitalId;

    @JsonProperty("orders")
    private Collection<ContractItemRequestDTO> orders;

    @JsonProperty("day")
    private int day;


}
