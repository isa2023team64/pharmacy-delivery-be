package com.isa2023team64.pharmacydeliverybe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa2023team64.pharmacydeliverybe.dto.RegisteredUserRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegisteredUserResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;
import com.isa2023team64.pharmacydeliverybe.service.EmailService;
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

    private Logger logger = LoggerFactory.getLogger(RegisteredUserController.class);

	@Autowired
	private EmailService emailService;

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

        try {
			System.out.println("Thread id: " + Thread.currentThread().getId());
			emailService.sendNotificaitionAsync(registeredUser);
		}catch( Exception e ){
			logger.info("Greska prilikom slanja emaila: " + e.getMessage());
		}

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

    @Operation(summary = "Activate registered user by ID", description = "Activates a registered user by ID", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registered user activated successfully.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RegisteredUserResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Registered user not found.", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
    })
    @PutMapping(value = "/activate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisteredUserResponseDTO> activateRegisteredUserById(@PathVariable int id) {
        RegisteredUser activatedUser = registeredUserService.activateRegisteredUser(id);

        if (activatedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RegisteredUserResponseDTO(activatedUser), HttpStatus.OK);
    }

}
