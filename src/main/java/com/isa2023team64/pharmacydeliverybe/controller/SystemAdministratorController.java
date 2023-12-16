package com.isa2023team64.pharmacydeliverybe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa2023team64.pharmacydeliverybe.dto.SystemAdministratorRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.SystemAdministratorResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.SystemAdministrator;
import com.isa2023team64.pharmacydeliverybe.service.SystemAdministratorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "System administrator controller", description = "System administrator API")
@RestController
@RequestMapping(value = "api/system-administrators")
public class SystemAdministratorController {
    
    @Autowired
    private SystemAdministratorService systemAdministratorService;

    @Operation(summary = "Get all system administrators", description = "Gets all system administrators.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All system administrators fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = SystemAdministrator.class))))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SystemAdministratorResponseDTO>> getAll() {
        List<SystemAdministrator> systemAdministrators = systemAdministratorService.findAll();

        List<SystemAdministratorResponseDTO> systemAdministratorResponseDTOs = new ArrayList<>();
        for (SystemAdministrator r : systemAdministrators) {
            systemAdministratorResponseDTOs.add(new SystemAdministratorResponseDTO(r));
        }

        return new ResponseEntity<>(systemAdministratorResponseDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Get system administrator by id", description = "Gets system administrator by id", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "System administrator fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     schema = @Schema(implementation = SystemAdministrator.class))),
        @ApiResponse(responseCode = "404", description = "Registered user not found.", content = @Content)
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SystemAdministratorResponseDTO> getCompanyAdministratorById(@PathVariable Integer id) {
        SystemAdministrator systemAdministrator = systemAdministratorService.findById(id);

        if (systemAdministrator == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new SystemAdministratorResponseDTO(systemAdministrator), HttpStatus.OK);
    }

    @Operation(summary = "Get system administrator by email", description = "Gets system administrator by email", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "System administrator fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     schema = @Schema(implementation = SystemAdministrator.class))),
        @ApiResponse(responseCode = "404", description = "Registered user not found.", content = @Content)
    })
    @PutMapping(value = "/by-email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SystemAdministratorResponseDTO> getCompanyAdministratorByEmail(@RequestBody String email) {
        SystemAdministrator systemAdministrator = systemAdministratorService.findByEmail(email);

        if (systemAdministrator == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new SystemAdministratorResponseDTO(systemAdministrator), HttpStatus.OK);
    }

    @Operation(summary = "Register new system administrator", description = "Registers new system administrator", method = "POST")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Created",
				     content = { @Content(mediaType = "application/json", schema = @Schema(implementation = SystemAdministrator.class)) })
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SystemAdministratorResponseDTO> registerSystemAdministrator(@RequestBody SystemAdministratorRequestDTO systemAdministratorRequestDTO) {
        SystemAdministrator systemAdministrator = new SystemAdministrator();

        systemAdministrator.setEmail(systemAdministratorRequestDTO.getEmail());
        systemAdministrator.setPassword(systemAdministratorRequestDTO.getPassword());
        systemAdministrator.setFirstName(systemAdministratorRequestDTO.getFirstName());
        systemAdministrator.setLastName(systemAdministratorRequestDTO.getLastName());
        systemAdministrator.setCity(systemAdministratorRequestDTO.getCity());
        systemAdministrator.setCountry(systemAdministratorRequestDTO.getCountry());
        systemAdministrator.setPhoneNumber(systemAdministratorRequestDTO.getPhoneNumber());
        systemAdministrator.setFirstLogged(systemAdministratorRequestDTO.getFirstLogged());

        systemAdministrator = systemAdministratorService.register(systemAdministrator);
        return new ResponseEntity<>(new SystemAdministratorResponseDTO(systemAdministrator), HttpStatus.CREATED);
    }


}
