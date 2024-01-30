package com.isa2023team64.pharmacydeliverybe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa2023team64.pharmacydeliverybe.dto.HospitalResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.Hospital;
import com.isa2023team64.pharmacydeliverybe.service.HospitalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Hospital controller", description = "Hospital API")
@RestController
@RequestMapping(value = "api/hospital")
@PreAuthorize("hasAnyRole('SYSTEMADMIN', 'COMPANYADMIN')")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @Operation(summary = "Get all hospitals", description = "Gets all hospitals.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All hospitals fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = Hospital.class))))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HospitalResponseDTO>> getAll() {
        List<Hospital> hospitals = hospitalService.findAll();

        List<HospitalResponseDTO> hospitalDTOs = new ArrayList<>();
        for(Hospital h : hospitals){
            hospitalDTOs.add(new HospitalResponseDTO(h));
        }

        return new ResponseEntity<>(hospitalDTOs, HttpStatus.OK);
    }
}
