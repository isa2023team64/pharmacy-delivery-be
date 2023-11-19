package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.SystemAdministrator;
import com.isa2023team64.pharmacydeliverybe.repository.SystemAdministratorRepository;

@Service
public class SystemAdministratorService {
    
    @Autowired
    private SystemAdministratorRepository systemAdministratorRepository;

    public SystemAdministrator findById(Integer id){
        return systemAdministratorRepository.findById(id).orElse(null);
    }

    public List<SystemAdministrator> findAll(){
        return systemAdministratorRepository.findAll();
    }

    public SystemAdministrator register(SystemAdministrator systemAdministrator) {
        return systemAdministratorRepository.save(systemAdministrator);
    }

    public SystemAdministrator update(SystemAdministrator systemAdministrator) {
        return systemAdministratorRepository.save(systemAdministrator);
    }
}
