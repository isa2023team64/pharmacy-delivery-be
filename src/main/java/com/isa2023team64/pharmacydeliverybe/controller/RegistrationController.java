package com.isa2023team64.pharmacydeliverybe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa2023team64.pharmacydeliverybe.dto.RegisteredUserRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegisteredUserResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;
import com.isa2023team64.pharmacydeliverybe.service.RegisteredUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Registration controller", description = "Registeration API")
@RestController
@RequestMapping(value = "api/registration")
public class RegistrationController {
    
    @Autowired
    private RegisteredUserService registeredUserService;

    @Operation(summary = "Register new user", description = "Registers new user", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RegisteredUser.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request - Email already in use", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisteredUserResponseDTO> registerUser(@RequestBody RegisteredUserRequestDTO registeredUserRequestDTO) {

        if (!isEmailUnique(registeredUserRequestDTO.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        RegisteredUser registeredUser=registeredUserService.saveRegisteredUser(registeredUserRequestDTO);

        return new ResponseEntity<>(new RegisteredUserResponseDTO(registeredUser), HttpStatus.CREATED);
    }    

    
    private boolean isEmailUnique(String email) {
        List<RegisteredUser> registeredUsers = registeredUserService.findAll();

        for (RegisteredUser user : registeredUsers) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

}
