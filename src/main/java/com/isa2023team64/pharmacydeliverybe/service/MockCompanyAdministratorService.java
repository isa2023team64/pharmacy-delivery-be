package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.MockCompanyAdministrator;
import com.isa2023team64.pharmacydeliverybe.repository.MockCompanyAdministratorRepository;

@Service
public class MockCompanyAdministratorService {
    
    @Autowired
    private MockCompanyAdministratorRepository mockCompanyAdministratorRepository;

    public MockCompanyAdministrator findById(Integer id){
        return mockCompanyAdministratorRepository.findById(id).orElse(null);
    }

    public List<MockCompanyAdministrator> findAll(){
        return mockCompanyAdministratorRepository.findAll();
    }

    // TODO:
    public MockCompanyAdministrator register(MockCompanyAdministrator mockCompanyAdministrator) {
        return mockCompanyAdministratorRepository.save(mockCompanyAdministrator);
    }
}
