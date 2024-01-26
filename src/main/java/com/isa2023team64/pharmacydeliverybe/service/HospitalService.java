package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import com.isa2023team64.pharmacydeliverybe.model.Hospital;

public interface HospitalService {
    public List<Hospital> findAll();

    public Hospital findById(int id);
}
