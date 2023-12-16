package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import com.isa2023team64.pharmacydeliverybe.model.User;


public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
    public User update(User user);
}
