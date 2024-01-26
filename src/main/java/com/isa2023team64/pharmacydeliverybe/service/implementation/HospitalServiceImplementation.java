package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.Hospital;
import com.isa2023team64.pharmacydeliverybe.repository.HospitalRepository;
import com.isa2023team64.pharmacydeliverybe.service.HospitalService;

@Service
public class HospitalServiceImplementation implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;
    
    public List<Hospital> findAll() {
        return hospitalRepository.findAll();
    }

    public Hospital findById( int id) {
        Hospital hospital = hospitalRepository.findById(id).orElse(null);
        return hospital;
    }
}
