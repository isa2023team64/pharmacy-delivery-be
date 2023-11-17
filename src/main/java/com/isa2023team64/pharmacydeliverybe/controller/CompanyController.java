package com.isa2023team64.pharmacydeliverybe.controller;


import org.hibernate.Hibernate;
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

import com.isa2023team64.pharmacydeliverybe.dto.CompanyRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanyResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.service.CompanyService;

import com.isa2023team64.pharmacydeliverybe.dto.MockCompanyAdministratorRequestDTO;
import com.isa2023team64.pharmacydeliverybe.service.MockCompanyAdministratorService;
import com.isa2023team64.pharmacydeliverybe.model.MockCompanyAdministrator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

@Tag(name = "Company controller", description = "Company API")
@RestController
@RequestMapping(value = "api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;  

    @Autowired
    private MockCompanyAdministratorService mockCompanyAdministratorService;

    @Operation(summary = "Get all companies", description = "Gets all companies.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All companies fetched successfully.",
                    content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Company.class))))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyResponseDTO>> getAll() {
        List<Company> companies = companyService.findAll();

        List<CompanyResponseDTO> companyDTOs = new ArrayList<>();
        for(Company c : companies){
            companyDTOs.add(new CompanyResponseDTO(c));
        }

        return new ResponseEntity<>(companyDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Get company by id", description = "Gets company by id", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Company fetched successfully.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Company.class))),
        @ApiResponse(responseCode = "404", description = "Company not found.", content = @Content)
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)   
    public ResponseEntity<CompanyResponseDTO> getCompanyById(@PathVariable Integer id) {
        Company company = companyService.findById(id);

        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CompanyResponseDTO(company), HttpStatus.OK);
    }

    @Operation(summary = "Register new company", description = "Registers new company", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created",
					     content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Company.class)) })
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyResponseDTO> registerCompany(@RequestBody CompanyRequestDTO companyRequestDTO) {
        Company company = new Company();

        String openingTimeDTO = companyRequestDTO.getOpeningTime();
        String closingTimeDTO = companyRequestDTO.getClosingTime();

        String[]openingTimeComponents = openingTimeDTO.split(":");
        String[]closingTimeComponents = closingTimeDTO.split(":");
        

        int openingHours = Integer.parseInt(openingTimeComponents[0]);
        int openingMinutes = Integer.parseInt(openingTimeComponents[1]);
        int openingSeconds = Integer.parseInt(openingTimeComponents[2]);
        
        int closingHours = Integer.parseInt(closingTimeComponents[0]);
        int closingMinutes = Integer.parseInt(closingTimeComponents[1]);
        int closingSeconds = Integer.parseInt(closingTimeComponents[2]);

        LocalTime openingTime = LocalTime.of(openingHours, openingMinutes, openingSeconds, 0);
        LocalTime closingTime = LocalTime.of(closingHours, closingMinutes, closingSeconds, 0);

        company.setName(companyRequestDTO.getName());
        company.setAddress(companyRequestDTO.getAddress());
        company.setCity(companyRequestDTO.getCity());
        company.setCountry(companyRequestDTO.getCountry());
        company.setOpeningTime(openingTime);
        company.setClosingTime(closingTime);
        company.setDescription(companyRequestDTO.getDescription());
        company.setAverageRating(companyRequestDTO.getAverageRating());

        company = companyService.register(company);

        List<MockCompanyAdministratorRequestDTO> mockCompanyAdministratorDTOs = companyRequestDTO.getCompanyAdministrators();

        if(mockCompanyAdministratorDTOs != null){
            List<MockCompanyAdministrator> mockCompanyAdministrators = new ArrayList<>();

            for(MockCompanyAdministratorRequestDTO mockCompanyAdministratorDTO : mockCompanyAdministratorDTOs){
                MockCompanyAdministrator mockCompanyAdministrator = new MockCompanyAdministrator();
                mockCompanyAdministrator.setUsername(mockCompanyAdministratorDTO.getUsername());
                mockCompanyAdministrator.setEmail(mockCompanyAdministratorDTO.getEmail());
                mockCompanyAdministrator.setPassword(mockCompanyAdministratorDTO.getPassword());
                
                mockCompanyAdministrator.setCompany(company);

                mockCompanyAdministrators.add(mockCompanyAdministrator);

                mockCompanyAdministratorService.register(mockCompanyAdministrator);
            }
            company.setCompanyAdministrators(mockCompanyAdministrators);
        }

        company = companyService.register(company);

        return new ResponseEntity<>(new CompanyResponseDTO(company), HttpStatus.CREATED);
    }


    @Operation(summary = "Register new company administrator in company", description = "Registers new company administrator in company", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created",
					     content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Company.class)) })
	})
    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyResponseDTO> registerCompanyAdministrator(@PathVariable Integer id, @RequestBody MockCompanyAdministratorRequestDTO mockCompanyAdministratorRequestDTO) {
        
        Company company = companyService.findById(id);

        List<MockCompanyAdministrator> mockCompanyAdministrators = company.getCompanyAdministrators();

        MockCompanyAdministrator mockCompanyAdministrator = new MockCompanyAdministrator();

        mockCompanyAdministrator.setUsername(mockCompanyAdministratorRequestDTO.getUsername());
        mockCompanyAdministrator.setEmail(mockCompanyAdministratorRequestDTO.getEmail());
        mockCompanyAdministrator.setPassword(mockCompanyAdministratorRequestDTO.getPassword());
        mockCompanyAdministrator.setCompany(company);

        mockCompanyAdministrators.add(mockCompanyAdministrator);        
        mockCompanyAdministratorService.register(mockCompanyAdministrator);
        
        company.setCompanyAdministrators(mockCompanyAdministrators);
        companyService.register(company);

        return new ResponseEntity<>(new CompanyResponseDTO(company), HttpStatus.CREATED);
    }

}
