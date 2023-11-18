package com.isa2023team64.pharmacydeliverybe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa2023team64.pharmacydeliverybe.dto.MockCompanyAdministratorRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.MockCompanyAdministratorResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.MockCompanyAdministrator;
import com.isa2023team64.pharmacydeliverybe.service.MockCompanyAdministratorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Mock company administrator controller", description = "Company administrator API")
@RestController
@RequestMapping(value = "api/mock-company-administrators")
public class MockCompanyAdministratorController {
    
    @Autowired
    private MockCompanyAdministratorService mockCompanyAdministratorService;

    @Operation(summary = "Get all company administrators", description = "Gets all company administrators.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All company administrators fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = MockCompanyAdministrator.class))))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MockCompanyAdministratorResponseDTO>> getAll() {
        List<MockCompanyAdministrator> mockCompanyAdministrators = mockCompanyAdministratorService.findAll();

        List<MockCompanyAdministratorResponseDTO> mockCompanyAdministratorsDTOs = new ArrayList<>();
        for (MockCompanyAdministrator r : mockCompanyAdministrators) {
            mockCompanyAdministratorsDTOs.add(new MockCompanyAdministratorResponseDTO(r));
        }

        return new ResponseEntity<>(mockCompanyAdministratorsDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Get company administrator by id", description = "Gets company administrator by id", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Company administrator fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     schema = @Schema(implementation = MockCompanyAdministrator.class))),
        @ApiResponse(responseCode = "404", description = "Registered user not found.", content = @Content)
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MockCompanyAdministratorResponseDTO> getCompanyAdministratorById(@PathVariable Integer id) {
        MockCompanyAdministrator mockCompanyAdministrator = mockCompanyAdministratorService.findById(id);

        if (mockCompanyAdministrator == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MockCompanyAdministratorResponseDTO(mockCompanyAdministrator), HttpStatus.OK);
    }

    @Operation(summary = "Register new company administrator", description = "Registers new company administrator", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created",
					     content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MockCompanyAdministrator.class)) })
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MockCompanyAdministratorResponseDTO> registerMockCompanyAdministrator(@RequestBody MockCompanyAdministratorRequestDTO mockCompanyAdministratorRequestDTO) {
        MockCompanyAdministrator mockCompanyAdministrator = new MockCompanyAdministrator();

        mockCompanyAdministrator.setEmail(mockCompanyAdministratorRequestDTO.getEmail());
        mockCompanyAdministrator.setPassword(mockCompanyAdministratorRequestDTO.getPassword());
        mockCompanyAdministrator.setFirstName(mockCompanyAdministratorRequestDTO.getFirstName());
        mockCompanyAdministrator.setLastName(mockCompanyAdministratorRequestDTO.getLastName());
        mockCompanyAdministrator.setCity(mockCompanyAdministratorRequestDTO.getCity());
        mockCompanyAdministrator.setCountry(mockCompanyAdministratorRequestDTO.getCountry());
        mockCompanyAdministrator.setPhoneNumber(mockCompanyAdministratorRequestDTO.getPhoneNumber());
        mockCompanyAdministrator.setWorkplace(mockCompanyAdministratorRequestDTO.getWorkplace());
        mockCompanyAdministrator.setCompanyName(mockCompanyAdministratorRequestDTO.getCompanyName());



        mockCompanyAdministrator = mockCompanyAdministratorService.register(mockCompanyAdministrator);
        return new ResponseEntity<>(new MockCompanyAdministratorResponseDTO(mockCompanyAdministrator), HttpStatus.CREATED);
    }
}
