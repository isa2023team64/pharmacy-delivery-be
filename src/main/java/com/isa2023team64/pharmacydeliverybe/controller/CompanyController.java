package com.isa2023team64.pharmacydeliverybe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa2023team64.pharmacydeliverybe.dto.CompanyNoAdminDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanyRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanyResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanySearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentSearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.service.CompanySearchService;
import com.isa2023team64.pharmacydeliverybe.service.EquipmentSearchService;
import com.isa2023team64.pharmacydeliverybe.service.CompanyService;
import com.isa2023team64.pharmacydeliverybe.util.PagedResult;
import com.isa2023team64.pharmacydeliverybe.util.WorkingHours;
import com.isa2023team64.pharmacydeliverybe.dto.AddEquipmentToCompanyDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanyAdministratorRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanyInfoRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanyInfoResponseDTO;
import com.isa2023team64.pharmacydeliverybe.service.CompanyAdministratorService;
import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;
import com.isa2023team64.pharmacydeliverybe.model.Equipment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalTime;

@Tag(name = "Company controller", description = "Company API")
@RestController
@RequestMapping(value = "api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanySearchService searchService;

    @Autowired
    private EquipmentSearchService equipmentSearchService;

    @Autowired
    private CompanyAdministratorService companyAdministratorService;

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
            c.setCompanyAdministrators(companyService.findCompanyAdministratorsByCompanyId(c.getId()));
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
                 
        List<CompanyAdministrator> companyAdministrators = companyService.findCompanyAdministratorsByCompanyId(company.getId());
        company.setCompanyAdministrators(companyAdministrators);

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
        
        int closingHours = Integer.parseInt(closingTimeComponents[0]);
        int closingMinutes = Integer.parseInt(closingTimeComponents[1]);

        LocalTime openingTime = LocalTime.of(openingHours, openingMinutes, 0, 0);
        LocalTime closingTime = LocalTime.of(closingHours, closingMinutes, 0, 0);

        company.setName(companyRequestDTO.getName());
        company.setAddress(companyRequestDTO.getAddress());
        company.setCity(companyRequestDTO.getCity());
        company.setCountry(companyRequestDTO.getCountry());
        company.setOpeningTime(openingTime);
        company.setClosingTime(closingTime);
        company.setDescription(companyRequestDTO.getDescription());
        company.setAverageRating(0);
        company.setImageURL("https://seeklogo.com/images/M/medical-company-logo-A572E099DB-seeklogo.com.png");

        company = companyService.register(company);

        List<CompanyAdministratorRequestDTO> companyAdministratorDTOs = companyRequestDTO.getCompanyAdministrators();

        if(companyAdministratorDTOs != null){
            List<CompanyAdministrator> companyAdministrators = new ArrayList<>();

            for(CompanyAdministratorRequestDTO companyAdministratorDTO : companyAdministratorDTOs){
                CompanyAdministrator companyAdministrator = new CompanyAdministrator();

                companyAdministrator.setEmail(companyAdministratorDTO.getEmail());
                companyAdministrator.setPassword(companyAdministratorDTO.getPassword());
                companyAdministrator.setFirstName(companyAdministratorDTO.getFirstName());
                companyAdministrator.setLastName(companyAdministratorDTO.getLastName());
                companyAdministrator.setCity(companyAdministratorDTO.getCity());
                companyAdministrator.setCountry(companyAdministratorDTO.getCountry());
                companyAdministrator.setPhoneNumber(companyAdministratorDTO.getPhoneNumber());
                companyAdministrator.setWorkplace(companyAdministratorDTO.getWorkplace());
                companyAdministrator.setCompanyName(companyAdministratorDTO.getCompanyName());

                companyAdministrator.setCompanyEntity(company);
                
                companyAdministrators.add(companyAdministrator);

                companyAdministratorService.register(companyAdministrator);
            }
            company.setCompanyAdministrators(companyAdministrators);
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
    public ResponseEntity<CompanyResponseDTO> registerCompanyAdministrator(@PathVariable Integer id, @RequestBody CompanyAdministratorRequestDTO companyAdministratorRequestDTO) {
        
        Company company = companyService.findById(id);

        // List<CompanyAdministrator> companyAdministrators = company.getCompanyAdministrators();
        List<CompanyAdministrator> companyAdministrators = companyService.findCompanyAdministratorsByCompanyId(company.getId());

        CompanyAdministrator companyAdministrator = new CompanyAdministrator();

        companyAdministrator.setEmail(companyAdministratorRequestDTO.getEmail());
        companyAdministrator.setPassword(companyAdministratorRequestDTO.getPassword());
        companyAdministrator.setFirstName(companyAdministratorRequestDTO.getFirstName());
        companyAdministrator.setLastName(companyAdministratorRequestDTO.getLastName());
        companyAdministrator.setCity(companyAdministratorRequestDTO.getCity());
        companyAdministrator.setCountry(companyAdministratorRequestDTO.getCountry());
        companyAdministrator.setPhoneNumber(companyAdministratorRequestDTO.getPhoneNumber());
        companyAdministrator.setWorkplace(companyAdministratorRequestDTO.getWorkplace());
        companyAdministrator.setCompanyName(companyAdministratorRequestDTO.getCompanyName());
        companyAdministrator.setActive(true);

        companyAdministrators.add(companyAdministrator);        
        companyAdministratorService.register(companyAdministrator);
        
        company.setCompanyAdministrators(companyAdministrators);
        companyService.register(company);

        return new ResponseEntity<>(new CompanyResponseDTO(company), HttpStatus.CREATED);
    }

    @Operation(summary = "Update company info", description = "Update company info", method = "PUT")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK",
                     content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Company.class)) }),
        @ApiResponse(responseCode = "404", description = "Company not found.", content = @Content)
	})
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyInfoResponseDTO> updateCompanyInfo(@PathVariable Integer id, @RequestBody CompanyInfoRequestDTO companyRequestDTO) {
        Company company = companyService.findById(id);

        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        company.setName(companyRequestDTO.getName());
        company.setAddress(companyRequestDTO.getAddress());
        company.setCity(companyRequestDTO.getCity());
        company.setCountry(companyRequestDTO.getCountry());
        company.setOpeningTime(WorkingHours.parseTime(companyRequestDTO.getOpeningTime()));
        company.setClosingTime(WorkingHours.parseTime(companyRequestDTO.getClosingTime()));
        company.setDescription(companyRequestDTO.getDescription());

        company = companyService.register(company);

        return new ResponseEntity<>(new CompanyInfoResponseDTO(company), HttpStatus.CREATED);
    }

    @Operation(summary = "Search, filter and sort all companies.", description = "Search, filter and sort all companies.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Companies searched fetched successfully.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PagedResult.class, subTypes = {CompanyNoAdminDTO.class})))
    })
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedResult<CompanyNoAdminDTO>> search(@ModelAttribute CompanySearchFilterDTO filter) {
        PagedResult<CompanyNoAdminDTO> companies = searchService.search(filter);

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }


    @GetMapping(value = "/{companyId}/equipment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EquipmentResponseDTO>> getCompanyEquipment(@PathVariable Integer companyId) {

        Company company = companyService.findOneWithEquipment(companyId);

        List<Equipment> equipment = company.getEquipment();
        List<EquipmentResponseDTO> equipmentResponseDTOs = new ArrayList<>();

        for (Equipment e : equipment) {
            equipmentResponseDTOs.add(new EquipmentResponseDTO(e));
        }

        return new ResponseEntity<>(equipmentResponseDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/search-by-equipment-filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedResult<CompanyNoAdminDTO>> searchCompanyByEquipment(@ModelAttribute EquipmentSearchFilterDTO filter) {

        List<Equipment> equipment = equipmentSearchService.searchEntities(filter);

        List<Integer> equipmentIds = equipment.stream().map(Equipment::getId).collect(Collectors.toList());

        PagedResult<CompanyNoAdminDTO> companyPage = companyService.findCompaniesByEquipmentIds(equipmentIds, filter);

        return new ResponseEntity<>(companyPage, HttpStatus.OK);
    }

    @GetMapping(value = "/by-equipment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedResult<CompanyInfoResponseDTO>> searchCompanyByEquipment(@PathVariable Integer id, @ModelAttribute CompanySearchFilterDTO filter) {

        PagedResult<CompanyInfoResponseDTO> companyPage = companyService.findCompaniesByEquipmentId(id, filter);

        return new ResponseEntity<>(companyPage, HttpStatus.OK);
    }

    @Operation(summary = "Get all companies without companies administrators", description = "Gets all companies without companies administrators.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All companies fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = Company.class))))
    })
    @GetMapping(value = "/no-company-administrators", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyInfoResponseDTO>> getAllWithoutCompanyAdministrators() {
        List<Company> companies = companyService.findAll();

        List<CompanyInfoResponseDTO> companyDTOs = new ArrayList<>();
        for(Company c : companies){
            companyDTOs.add(new CompanyInfoResponseDTO(c));
        }

        return new ResponseEntity<>(companyDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Get company without company's administrators by id", description = "Gets company without company's administrators by id", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Company fetched successfully.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Company.class))),
        @ApiResponse(responseCode = "404", description = "Company not found.", content = @Content)
    })
    @GetMapping(value = "/no-company-administrators/{id}", produces = MediaType.APPLICATION_JSON_VALUE)   
    public ResponseEntity<CompanyInfoResponseDTO> getCompanyWithoutCompanyAdministratorsById(@PathVariable Integer id) {
        
        Company company = companyService.findById(id);
                 
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CompanyInfoResponseDTO(company), HttpStatus.OK);
    }

    @Operation(summary = "Add equipment to company", description = "Add equipment to company", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Company fetched successfully.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Company.class))),
        @ApiResponse(responseCode = "404", description = "Company not found.", content = @Content)
    })
    @PutMapping(value = "add-equipment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddEquipmentToCompanyDTO> addEquipmentToCompany(@RequestBody AddEquipmentToCompanyDTO dto) {
        Integer companyId = dto.getCompanyId();
        Integer equipmentId = dto.getEquipmentId();

        this.companyService.addEquipmentToCompany(companyId, equipmentId);

        return new ResponseEntity<>(new AddEquipmentToCompanyDTO(companyId, equipmentId), HttpStatus.OK);
    }

    @Operation(summary = "Remove equipment to company", description = "Remove equipment from company", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Company fetched successfully.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Company.class))),
        @ApiResponse(responseCode = "404", description = "Company not found.", content = @Content)
    })
    @PutMapping(value = "remove-equipment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddEquipmentToCompanyDTO> deleteEquipmentFromCompany(@RequestBody AddEquipmentToCompanyDTO dto) {
        Integer companyId = dto.getCompanyId();
        Integer equipmentId = dto.getEquipmentId();

        this.companyService.removeEquipmentToCompany(companyId, equipmentId);

        return new ResponseEntity<>(new AddEquipmentToCompanyDTO(companyId, equipmentId), HttpStatus.OK);
    }
}
