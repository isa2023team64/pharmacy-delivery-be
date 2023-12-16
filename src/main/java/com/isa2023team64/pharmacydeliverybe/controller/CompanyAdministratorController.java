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

import com.isa2023team64.pharmacydeliverybe.dto.CompanyAdministratorRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanyAdministratorResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;
import com.isa2023team64.pharmacydeliverybe.service.CompanyAdministratorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Company administrator controller", description = "Company administrator API")
@RestController
@RequestMapping(value = "api/company-administrators")
public class CompanyAdministratorController {
    
    @Autowired
    private CompanyAdministratorService CompanyAdministratorService;

    @Operation(summary = "Get all company administrators", description = "Gets all company administrators.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All company administrators fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = CompanyAdministrator.class))))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyAdministratorResponseDTO>> getAll() {
        List<CompanyAdministrator> companyAdministrators = CompanyAdministratorService.findAll();

        List<CompanyAdministratorResponseDTO> companyAdministratorsDTOs = new ArrayList<>();
        for (CompanyAdministrator r : companyAdministrators) {
            companyAdministratorsDTOs.add(new CompanyAdministratorResponseDTO(r));
        }

        return new ResponseEntity<>(companyAdministratorsDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Get company administrator by id", description = "Gets company administrator by id", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Company administrator fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     schema = @Schema(implementation = CompanyAdministratorResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Registered user not found.", content = @Content)
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyAdministratorResponseDTO> getCompanyAdministratorById(@PathVariable Integer id) {
        CompanyAdministrator companyAdministrator = CompanyAdministratorService.findById(id);

        if (companyAdministrator == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CompanyAdministratorResponseDTO(companyAdministrator), HttpStatus.OK);
    }

    @Operation(summary = "Register new company administrator", description = "Registers new company administrator", method = "POST")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Created",
				     content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyAdministrator.class)) })
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyAdministratorResponseDTO> registerCompanyAdministrator(@RequestBody CompanyAdministratorRequestDTO companyAdministratorRequestDTO) {
        CompanyAdministrator companyAdministrator = new CompanyAdministrator();

        companyAdministrator.setEmail(companyAdministratorRequestDTO.getEmail());
        companyAdministrator.setPassword(companyAdministratorRequestDTO.getPassword());
        companyAdministrator.setFirstName(companyAdministratorRequestDTO.getFirstName());
        companyAdministrator.setLastName(companyAdministratorRequestDTO.getLastName());
        companyAdministrator.setCity(companyAdministratorRequestDTO.getCity());
        companyAdministrator.setCountry(companyAdministratorRequestDTO.getCountry());
        companyAdministrator.setPhoneNumber(companyAdministratorRequestDTO.getPhoneNumber());

        companyAdministrator = CompanyAdministratorService.register(companyAdministrator);
        return new ResponseEntity<>(new CompanyAdministratorResponseDTO(companyAdministrator), HttpStatus.CREATED);
    }

    @Operation(summary = "Update company administrator profile", description = "Update company administrator profile", method = "PUT")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK",
                     content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyAdministrator.class)) }),
        @ApiResponse(responseCode = "404", description = "Company administrator not found.", content = @Content)
	})
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyAdministratorResponseDTO> updateCompanyAdministratorProfile(@PathVariable Integer id, @RequestBody CompanyAdministratorRequestDTO companyAdministratorRequestDTO) {
        CompanyAdministrator companyAdministrator = CompanyAdministratorService.findById(id);

        if (companyAdministrator == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        companyAdministrator.setEmail(companyAdministratorRequestDTO.getEmail());
        companyAdministrator.setPassword(companyAdministratorRequestDTO.getPassword());
        companyAdministrator.setFirstName(companyAdministratorRequestDTO.getFirstName());
        companyAdministrator.setLastName(companyAdministratorRequestDTO.getLastName());
        companyAdministrator.setCity(companyAdministratorRequestDTO.getCity());
        companyAdministrator.setCountry(companyAdministratorRequestDTO.getCountry());
        companyAdministrator.setPhoneNumber(companyAdministratorRequestDTO.getPhoneNumber());

        companyAdministrator = CompanyAdministratorService.register(companyAdministrator);
        return new ResponseEntity<>(new CompanyAdministratorResponseDTO(companyAdministrator), HttpStatus.CREATED);
    }
}
