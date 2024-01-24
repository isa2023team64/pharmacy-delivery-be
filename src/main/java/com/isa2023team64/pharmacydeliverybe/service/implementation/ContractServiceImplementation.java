package com.isa2023team64.pharmacydeliverybe.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.repository.ContractRepository;
import com.isa2023team64.pharmacydeliverybe.service.ContractService;

@Service
public class ContractServiceImplementation implements ContractService {

    @Autowired
    private ContractRepository contractRepository;  
}