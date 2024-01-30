package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.dto.RegisteredUserRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegisteredUserUpdateDTO;
import com.isa2023team64.pharmacydeliverybe.model.Hospital;
import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;
import com.isa2023team64.pharmacydeliverybe.model.Role;
import com.isa2023team64.pharmacydeliverybe.repository.RegisteredUserRepository;
import com.isa2023team64.pharmacydeliverybe.service.RegisteredUserService;
import com.isa2023team64.pharmacydeliverybe.service.RoleService;

import jakarta.persistence.EntityNotFoundException;

@Service
@EnableScheduling
public class RegisteredUserServiceImplementation implements RegisteredUserService {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
	private PasswordEncoder passwordEncoder;
    
    @Autowired
	private RoleService roleService;

    @Override
    public RegisteredUser saveRegisteredUser(RegisteredUserRequestDTO user) {
        RegisteredUser u = new RegisteredUser();
		
		// pre nego sto postavimo lozinku u atribut hesiramo je kako bi se u bazi nalazila hesirana lozinka
		// treba voditi racuna da se koristi isi password encoder bean koji je postavljen u AUthenticationManager-u kako bi koristili isti algoritam
		u.setEmail(user.getEmail());
        u.setPassword(passwordEncoder.encode(user.getPassword()));
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setActive(false);
        u.setCity(user.getCity());
        u.setCountry(user.getCountry());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setLastPasswordResetDate(new Timestamp(new Date().getTime()));
        u.setWorkplace(user.getWorkplace());

        Hospital hospital = new Hospital(user.getCompanyName(), user.getLongitude(), user.getLatitude());
        u.setHospital(hospital);
        u.setPenaltyPoints(0);
        List<Role> roles = roleService.findByName("ROLE_USER");
		u.setRoles(roles);
		
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

    @Override
    public RegisteredUser update(int id, RegisteredUserUpdateDTO updatedUser) {
        RegisteredUser user = registeredUserRepository.findById(id);
        if(user == null) {
            throw new EntityNotFoundException("User not found.");
        }

        if(!updatedUser.getPassword().equals(updatedUser.getPasswordConfirmation())) {
            throw new IllegalArgumentException("Password and confirmation don't match.");
        }

        if (!updatedUser.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        user.setLastPasswordResetDate(new Timestamp(new Date().getTime()));
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setCity(updatedUser.getCity());
        user.setCountry(updatedUser.getCountry());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setWorkplace(updatedUser.getWorkplace());
        user.setPenaltyPoints(updatedUser.getPenaltyPoints());
        user.getHospital().setName(updatedUser.getCompanyName());
        registeredUserRepository.save(user);

        return user;
    }

    @Override
    public RegisteredUser addPenaltyPoints(int id, boolean dayBefore) {
        RegisteredUser user = registeredUserRepository.findById(id);
        if(user == null) {
            throw new EntityNotFoundException("User not found.");
        }
        if(dayBefore){
            user.setPenaltyPoints(user.getPenaltyPoints()+2);
        }
        else{
            user.setPenaltyPoints(user.getPenaltyPoints()+1);
        }
        registeredUserRepository.save(user);
        return user;
    }

    @Override
    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void removePenaltyPoints() {
        int currentDateDay = LocalDate.now().getDayOfMonth();
        if(currentDateDay != 1) return;

        List<RegisteredUser> users = registeredUserRepository.findAll();
        for(var user : users) {
            user.setPenaltyPoints(0);
            registeredUserRepository.save(user);
        }
    }
    
}
