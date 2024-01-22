package com.isa2023team64.pharmacydeliverybe.dto;

import java.sql.Timestamp;
import java.util.List;

import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;
import com.isa2023team64.pharmacydeliverybe.model.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisteredUserResponseDTO extends RegisteredUserRequestDTO {

    private Integer id;
    private boolean active;
    private Timestamp lastPasswordResetDate;
    private List<Role> roles;

    public RegisteredUserResponseDTO(RegisteredUser registeredUser) {
        this(registeredUser.getId(), registeredUser.getEmail(), registeredUser.getPassword(), registeredUser.getFirstName(), registeredUser.getLastName(),registeredUser.isActive(),registeredUser.getLastPasswordResetDate(),registeredUser.getCity(),registeredUser.getCountry(),registeredUser.getPhoneNumber(),registeredUser.getWorkplace(),registeredUser.getCompanyName(), registeredUser.getRoles(), registeredUser.getPenaltyPoints());
    }

    public RegisteredUserResponseDTO(Integer id, String email, String password, String firstName, String lastName, Boolean active, Timestamp lastPasswordResetDate, String city, String country,String phoneNumber, String workplace, String company, List<Role> roles, int penaltyPoints) {
        super(email, password, firstName, lastName, city, country, phoneNumber, workplace, company, penaltyPoints);

        this.id = id;
        this.active = active;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.roles= roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


}    
