package com.isa2023team64.pharmacydeliverybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isa2023team64.pharmacydeliverybe.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByName(String name);

	@Query("SELECT u.roles FROM User u WHERE u.id = :userId")
	List<Role> findRolesByUserId(@Param("userId") int userId);
	
}
