package com.isa2023team64.pharmacydeliverybe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentSearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.mapper.EquipmentDTOMapper;
import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.model.Equipment;
import com.isa2023team64.pharmacydeliverybe.service.CompanyService;
import com.isa2023team64.pharmacydeliverybe.service.EquipmentSearchService;
import com.isa2023team64.pharmacydeliverybe.service.EquipmentService;
import com.isa2023team64.pharmacydeliverybe.util.PagedResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Equipment controller", description = "Equipment API")
@RestController
@RequestMapping(value = "api/equipment")
public class EquipmentController {
    
    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EquipmentSearchService searchService;

    
    @Operation(summary = "Get all equipment", description = "Gets all equipment.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All equipment fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = Equipment.class))))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EquipmentResponseDTO>> getAll() {
        List<Equipment> equipment = equipmentService.findAll();

        List<EquipmentResponseDTO> equipmentDTOs = new ArrayList<>();
        for(Equipment e : equipment){
            equipmentDTOs.add(new EquipmentResponseDTO(e));
        }

        return new ResponseEntity<>(equipmentDTOs, HttpStatus.OK);
    }


    @Operation(summary = "Search, filter and sort all equipment.", description = "Search, filter and sort all equipment.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eqipment searched fetched successfully.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PagedResult.class, subTypes = {EquipmentRequestDTO.class})))
    })
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedResult<EquipmentResponseDTO>> search(@ModelAttribute EquipmentSearchFilterDTO filter) {
        PagedResult<EquipmentResponseDTO> equipment = searchService.search(filter);

        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    @Operation(summary = "Search, filter and sort all equipment for company administrator.", description = "Search, filter and sort all equipment for company administrator", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eqipment searched fetched successfully.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PagedResult.class, subTypes = {EquipmentRequestDTO.class})))
    })
    @GetMapping(value = "/search/company-administrator/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedResult<EquipmentResponseDTO>> searchWithCompanyAdministrator(@PathVariable Integer id, @ModelAttribute EquipmentSearchFilterDTO filter) {
        PagedResult<EquipmentResponseDTO> equipment = searchService.searchWithCompanyAdministrator(id, filter);

        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    @Operation(summary = "Get equipment not owned by company", description = "Get equipment not owned by company", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All equipment fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = Equipment.class))))
    })
    @GetMapping(value = "/not-owned-by-company/{companyId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EquipmentResponseDTO>> getNotOwnedByCompany(@PathVariable Integer companyId) {
        List<Equipment> equipment = companyService.findOneWithEquipment(companyId).getEquipment();
        List<Equipment> allEquipment = equipmentService.findAll();
        List<Equipment> notOwned = equipmentService.findAll();

        for (Equipment eq : allEquipment) {
            if (equipment.contains(eq)) {
                notOwned.remove(eq);
            }
        }

        List<EquipmentResponseDTO> equipmentDTOs = new ArrayList<>();
        for(Equipment e : notOwned) {
            equipmentDTOs.add(new EquipmentResponseDTO(e));
        }

        return new ResponseEntity<>(equipmentDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Register new equipment", description = "Registers new equipment", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created",
					     content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Company.class)) })
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EquipmentResponseDTO> registerEquipment(@RequestBody EquipmentRequestDTO dto) {
        Equipment equipment = EquipmentDTOMapper.fromRequestDTO(dto);
        equipment.setId(null);
        equipmentService.save(equipment);
        return new ResponseEntity<>(new EquipmentResponseDTO(equipment), HttpStatus.CREATED);
    }

    @Operation(summary = "Update equipment", description = "Updates new equipment", method = "PUT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created",
					     content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Company.class)) })
	})
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EquipmentResponseDTO> updateEquipment(@PathVariable Integer id, @RequestBody EquipmentRequestDTO dto) {
        Equipment equipment = equipmentService.findById(id);

        if (equipment == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        equipment.setName(dto.getName());
        equipment.setDescription(dto.getDescription());
        equipment.setType(dto.getType());
        equipment.setStockCount(dto.getStockCount());

        equipmentService.save(equipment);

        return new ResponseEntity<>(new EquipmentResponseDTO(equipment), HttpStatus.CREATED);
    }

    @Operation(summary = "Get equipment by id", description = "Get equipment by id", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK",
					     content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Company.class)) })
	})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EquipmentResponseDTO> getById(@PathVariable int id) {
        Equipment equipment = equipmentService.findById(id);

        if (equipment == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new EquipmentResponseDTO(equipment), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete equipment by id", description = "Delete equipment by id", method = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK")})
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        try {
            Equipment equipment = equipmentService.findById(id);
            equipmentService.delete(equipment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
