package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;


import com.isa2023team64.pharmacydeliverybe.dto.RegisteredUserRequestDTO;
import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;

public interface RegisteredUserService {

    public RegisteredUser findByEmail(String email);

    public RegisteredUser findById(int id);

    public List<RegisteredUser> findAll();

    public RegisteredUser saveRegisteredUser(RegisteredUserRequestDTO registeredUserRequestDTO);

    public RegisteredUser activateRegisteredUser(int id);
    
}
