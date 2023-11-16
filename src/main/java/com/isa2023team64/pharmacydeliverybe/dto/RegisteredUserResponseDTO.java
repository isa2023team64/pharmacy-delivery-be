package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;

public class RegisteredUserResponseDTO extends RegisteredUserRequestDTO {
    private Integer id;
    private boolean active;

    public RegisteredUserResponseDTO() {
        super();
    }

    public RegisteredUserResponseDTO(RegisteredUser registeredUser) {
        this(registeredUser.getId(), registeredUser.getEmail(), registeredUser.getPassword(), registeredUser.getFirstName(), registeredUser.getLastName(),registeredUser.isActive());
    }

    public RegisteredUserResponseDTO(Integer id, String email, String password, String firstName, String lastName, Boolean active) {
        super(email, password, firstName, lastName);
        this.id = id;
        this.active = active;
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
}    
