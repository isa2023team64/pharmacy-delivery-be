package com.isa2023team64.pharmacydeliverybe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;
import com.isa2023team64.pharmacydeliverybe.repository.RegisteredUserRepository;

import java.util.List;

@Service
public class RegisteredUserService {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    public RegisteredUser findById(Integer id) {
        return registeredUserRepository.findById(id).orElse(null);
    }

    public List<RegisteredUser> findAll() {
        return registeredUserRepository.findAll();
    }

    // TODO: proveriti da li su username i email jedinstveni
    public RegisteredUser register(RegisteredUser registeredUser) {
        return registeredUserRepository.save(registeredUser);
    }
}
