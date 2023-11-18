package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.dto.RegisteredUserRequestDTO;
import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;
import com.isa2023team64.pharmacydeliverybe.repository.RegisteredUserRepository;
import com.isa2023team64.pharmacydeliverybe.service.RegisteredUserService;

@Service
public class RegisteredUserServiceImplementation implements RegisteredUserService {

    @Autowired
	//private PasswordEncoder passwordEncoder;
    private RegisteredUserRepository registeredUserRepository;

    @Override
    public RegisteredUser saveRegisteredUser(RegisteredUserRequestDTO user) {
        RegisteredUser u = new RegisteredUser();
		
		// pre nego sto postavimo lozinku u atribut hesiramo je kako bi se u bazi nalazila hesirana lozinka
		// treba voditi racuna da se koristi isi password encoder bean koji je postavljen u AUthenticationManager-u kako bi koristili isti algoritam
		u.setEmail(user.getEmail());
        //u.setPassword(passwordEncoder.encode(user.getPassword()));
        u.setPassword(user.getPassword());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setActive(false);
        u.setCity(user.getCity());
        u.setCountry(user.getCountry());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setLastPasswordResetDate(new Timestamp(new Date().getTime()));
        u.setWorkplace(user.getWorkplace());
        u.setCompanyName(user.getCompanyName());
		
		return registeredUserRepository.save(u);
    }

    @Override
    public RegisteredUser findById(int id) {
        return registeredUserRepository.findById(id);
    }

    @Override
    public RegisteredUser findByEmail(String email) {
        return registeredUserRepository.findByEmail(email);
    }

    @Override
    public List<RegisteredUser> findAll() {
        return registeredUserRepository.findAll();
    }

    @Override
    public RegisteredUser activateRegisteredUser(int id) {
        RegisteredUser u = registeredUserRepository.findById(id);

        if (u != null) {
            u.setActive(true);            
            return registeredUserRepository.save(u);
        }
        
        return null;
    }
    
}
