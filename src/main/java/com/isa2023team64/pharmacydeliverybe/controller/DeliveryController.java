package com.isa2023team64.pharmacydeliverybe.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa2023team64.pharmacydeliverybe.dto.DeliveryResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.Delivery;
import com.isa2023team64.pharmacydeliverybe.service.DeliveryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Delivery controller", description = "Equipment API")
@RestController
@RequestMapping(value = "api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @Operation(summary = "Get all deliveries", description = "Gets all deliveries.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All deliveries fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = DeliveryResponseDTO.class))))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<DeliveryResponseDTO>> getUndeliveredDeliveries() {
        Collection<Delivery> deliveries = deliveryService.findAll();

        Collection<DeliveryResponseDTO> deliveryDTOs = new ArrayList<>();
        for(var delivery : deliveries){
            deliveryDTOs.add(new DeliveryResponseDTO(delivery));
        }

        return new ResponseEntity<>(deliveryDTOs, HttpStatus.OK);
    }

}
