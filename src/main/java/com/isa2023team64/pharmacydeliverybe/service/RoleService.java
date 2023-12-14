package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import com.isa2023team64.pharmacydeliverybe.model.Role;

public interface RoleService {
	Role findById(Long id);
	List<Role> findByName(String name);
}
