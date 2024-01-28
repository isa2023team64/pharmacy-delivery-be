package com.isa2023team64.pharmacydeliverybe.service;

import com.isa2023team64.pharmacydeliverybe.dto.ContractRequestDTO;
import com.isa2023team64.pharmacydeliverybe.model.Contract;

public interface ContractService {
    
    public Contract save(ContractRequestDTO contractRequestDTO);

}

