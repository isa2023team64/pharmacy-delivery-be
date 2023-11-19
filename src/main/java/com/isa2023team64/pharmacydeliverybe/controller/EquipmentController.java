package com.isa2023team64.pharmacydeliverybe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa2023team64.pharmacydeliverybe.dto.EquipmentRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentSearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.model.Equipment;
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
}
