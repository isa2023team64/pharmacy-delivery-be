package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;

public class RegisteredUserResponseDTO extends RegisteredUserRequestDTO {
    private Integer id;

    public RegisteredUserResponseDTO() {
        super();
    }

    public RegisteredUserResponseDTO(RegisteredUser registeredUser) {
        this(registeredUser.getId(), registeredUser.getUsername(), registeredUser.getEmail(), registeredUser.getPassword(), registeredUser.getFirstName(), registeredUser.getLastName());
    }

    public RegisteredUserResponseDTO(Integer id, String username, String email, String password, String firstName, String lastName) {
        super(username, email, password, firstName, lastName);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
