package com.isa2023team64.pharmacydeliverybe.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa2023team64.pharmacydeliverybe.model.Contract;
import com.isa2023team64.pharmacydeliverybe.model.Hospital;

public interface ContractRepository extends JpaRepository<Contract, Integer>{
    
    public void deleteByHospital(Hospital hospital);

}
