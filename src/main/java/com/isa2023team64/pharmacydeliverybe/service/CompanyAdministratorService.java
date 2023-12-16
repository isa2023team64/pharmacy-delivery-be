package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;
import com.isa2023team64.pharmacydeliverybe.repository.CompanyAdministratorRepository;

@Service
public class CompanyAdministratorService {
    
    @Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    private CompanyAdministratorRepository companyAdministratorRepository;

    public CompanyAdministrator findById(Integer id){
        return companyAdministratorRepository.findById(id).orElse(null);
    }

    public List<CompanyAdministrator> findAll(){
        return companyAdministratorRepository.findAll();
    }

    public CompanyAdministrator register(CompanyAdministrator companyAdministrator) {
        companyAdministrator.setPassword(passwordEncoder.encode(companyAdministrator.getPassword()));
        return companyAdministratorRepository.save(companyAdministrator);
    }

    public CompanyAdministrator update(CompanyAdministrator companyAdministrator) {
        return companyAdministratorRepository.save(companyAdministrator);
    }
}
