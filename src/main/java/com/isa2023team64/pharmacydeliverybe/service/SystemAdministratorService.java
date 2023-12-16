package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;
import com.isa2023team64.pharmacydeliverybe.model.SystemAdministrator;
import com.isa2023team64.pharmacydeliverybe.repository.SystemAdministratorRepository;

@Service
public class SystemAdministratorService {
    
    @Autowired
    private SystemAdministratorRepository systemAdministratorRepository;

    @Autowired
	private PasswordEncoder passwordEncoder;

    public SystemAdministrator findById(Integer id){
        return systemAdministratorRepository.findById(id).orElse(null);
    }

    public List<SystemAdministrator> findAll(){
        return systemAdministratorRepository.findAll();
    }

    public SystemAdministrator register(SystemAdministrator systemAdministrator) {
        systemAdministrator.setPassword(passwordEncoder.encode(systemAdministrator.getPassword()));        
        return systemAdministratorRepository.save(systemAdministrator);
    }

    public SystemAdministrator update(SystemAdministrator systemAdministrator) {
        // systemAdministrator.setPassword(passwordEncoder.encode(systemAdministrator.getPassword()));
        return systemAdministratorRepository.save(systemAdministrator);
    }

    
    public SystemAdministrator findByEmail(String email){
        return systemAdministratorRepository.findSystemAdministratorByEmail(email);
    }

    public SystemAdministrator findByUserId(int userId){
        return systemAdministratorRepository.findSystemAdministratorByUserId(userId);
    }
}
