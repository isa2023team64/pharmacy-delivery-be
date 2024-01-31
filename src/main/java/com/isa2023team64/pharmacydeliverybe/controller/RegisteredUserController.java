package com.isa2023team64.pharmacydeliverybe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa2023team64.pharmacydeliverybe.dto.RegisteredUserResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegisteredUserUpdateDTO;
import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;
import com.isa2023team64.pharmacydeliverybe.service.RegisteredUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Registered user controller", description = "Registered user API")
@RestController
@RequestMapping(value = "api/registered-users")
public class RegisteredUserController {
    
    @Autowired
    private RegisteredUserService registeredUserService;

    
    @Operation(summary = "Get all registered users", description = "Gets all registered users.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All registered users fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = RegisteredUser.class))))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('SYSTEMADMIN')")
    public ResponseEntity<List<RegisteredUserResponseDTO>> getAll() {
        List<RegisteredUser> registeredUsers = registeredUserService.findAll();

        List<RegisteredUserResponseDTO> registeredUsersDTOs = new ArrayList<>();
        for (RegisteredUser r : registeredUsers) {
            registeredUsersDTOs.add(new RegisteredUserResponseDTO(r));
        }

        return new ResponseEntity<>(registeredUsersDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Get registered user by id", description = "Gets registered user by id", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registered user fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     schema = @Schema(implementation = RegisteredUser.class))),
        @ApiResponse(responseCode = "404", description = "Registered user not found.", content = @Content)
    })
    @PreAuthorize("hasAnyRole('USER', 'SYSTEMADMIN', 'COMPANYADMIN')")
    @GetMapping(value = "/by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisteredUserResponseDTO> getRegisteredUserById(@PathVariable int id) {
        RegisteredUser registeredUser = registeredUserService.findById(id);
        if (registeredUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RegisteredUserResponseDTO(registeredUser), HttpStatus.OK);
    }

    @Operation(summary = "Get registered user by email", description = "Gets registered user by email", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registered user fetched successfully.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RegisteredUser.class))),
        @ApiResponse(responseCode = "404", description = "Registered user not found.", content = @Content)
    })
    @GetMapping(value = "/by-email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('USER', 'SYSTEMADMIN', 'COMPANYADMIN')")
    public ResponseEntity<RegisteredUserResponseDTO> getRegisteredUserByEmail(@PathVariable String email) {
        RegisteredUser registeredUser = registeredUserService.findByEmail(email);
        if (registeredUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RegisteredUserResponseDTO(registeredUser), HttpStatus.OK);
    }

    @Operation(summary = "Update user.", description = "Update user.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated successfully.",
                     content = @Content(mediaType = "application/json",
                     schema = @Schema(implementation = RegisteredUser.class))),
        @ApiResponse(responseCode = "400", description = "Password and confirmation don't match.",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "User not found.",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = String.class)))
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody RegisteredUserUpdateDTO updatedUser) {
        try {
            RegisteredUser user = registeredUserService.update(id, updatedUser);
            return new ResponseEntity<>(new RegisteredUserResponseDTO(user), HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("Password and confirmation don't match.", HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update user penalty points.", description = "Update user penalty points.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User penalty points updated successfully.",
                     content = @Content(mediaType = "application/json",
                     schema = @Schema(implementation = RegisteredUser.class))),
        @ApiResponse(responseCode = "404", description = "User not found.",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = String.class)))
    })
    @PutMapping(value = "/penaltyPoints/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('USER', 'SYSTEMADMIN')")
    public ResponseEntity<?> addPenaltyPoints(@PathVariable int id, @RequestBody boolean dayBefore) {
        try {
            RegisteredUser user = registeredUserService.addPenaltyPoints(id, dayBefore);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
    }

}
