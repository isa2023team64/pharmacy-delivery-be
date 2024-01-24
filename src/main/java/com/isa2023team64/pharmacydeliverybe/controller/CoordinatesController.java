package com.isa2023team64.pharmacydeliverybe.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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
import org.springframework.web.reactive.function.client.WebClient;

import com.isa2023team64.pharmacydeliverybe.dto.CoordinatesResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentSearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.mapper.EquipmentDTOMapper;
import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.model.Coordinates;
import com.isa2023team64.pharmacydeliverybe.model.Equipment;
import com.isa2023team64.pharmacydeliverybe.service.CompanyService;
import com.isa2023team64.pharmacydeliverybe.service.CoordinatesService;
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


@Tag(name = "Coordinates controller", description = "Coordinates API")
@RestController
@RequestMapping(value = "api/coordinates")
public class CoordinatesController {


    @Autowired
    private CoordinatesService coordinatesService;




    @Operation(summary = "Get coordinates by id", description = "Gets coordinates by id", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Coordinates fetched successfully.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Coordinates.class))),
        @ApiResponse(responseCode = "404", description = "Coordinates not found.", content = @Content)
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)   
    public ResponseEntity<CoordinatesResponseDTO> getCoordinatesyById(@PathVariable Integer id) {
        Coordinates coordinates = coordinatesService.findById(id);
                 
        if (coordinates == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(new CoordinatesResponseDTO(coordinates), HttpStatus.OK);
    }




    @Operation(summary = "Make delivery with coordinates", description = "Makes delivery with given coordinates", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Coordinates fetched successfully.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Coordinates.class))),
        @ApiResponse(responseCode = "404", description = "Coordinates not found.", content = @Content)
    })
    @PostMapping(value = "/delivery", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)   
    public ResponseEntity<Void> makeDelivery(@RequestBody List<Coordinates> coordinatesList) {
        try{

            String locationSimulatorUrl = "http://localhost:8001";

            WebClient webClient = WebClient.create();

            URI locationSimulatorURI = new URI(locationSimulatorUrl + "/delivery");

            webClient.method(HttpMethod.POST).uri(locationSimulatorURI).bodyValue(coordinatesList).retrieve().toBodilessEntity().block();

            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }



}
