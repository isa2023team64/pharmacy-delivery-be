package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;
import com.isa2023team64.pharmacydeliverybe.repository.CompanyAdministratorRepository;

@Service
public class CompanyAdministratorService {
    
    @Autowired
    private CompanyAdministratorRepository companyAdministratorRepository;

    public CompanyAdministrator findById(Integer id){
        return companyAdministratorRepository.findById(id).orElse(null);
    }

    public List<CompanyAdministrator> findAll(){
        return companyAdministratorRepository.findAll();
    }

    // TODO:
    public CompanyAdministrator register(CompanyAdministrator companyAdministrator) {
        return companyAdministratorRepository.save(companyAdministrator);
    }
}
