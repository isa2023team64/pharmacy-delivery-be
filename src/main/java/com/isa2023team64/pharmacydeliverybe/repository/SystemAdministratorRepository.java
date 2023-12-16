package com.isa2023team64.pharmacydeliverybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;
import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;
import com.isa2023team64.pharmacydeliverybe.model.SystemAdministrator;

public interface SystemAdministratorRepository extends JpaRepository<SystemAdministrator, Integer> {
    RegisteredUser findByEmail(String email);

    @Query("SELECT sa FROM SystemAdministrator sa WHERE sa.email = :email")
    public SystemAdministrator findSystemAdministratorByEmail(@Param("email") String email);

    @Query("SELECT sa FROM SystemAdministrator sa WHERE sa.id = :userId")
    public SystemAdministrator findSystemAdministratorByUserId(@Param("userId") int userId);
}
