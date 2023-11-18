package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.repository.CompanyRepository;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    public Company findById(Integer id){
        return companyRepository.findById(id).orElse(null);
    }

    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    public Company register(Company company) {
        return companyRepository.save(company);
    }

    public Company update(Company company) {
        return companyRepository.save(company);
    }
}
