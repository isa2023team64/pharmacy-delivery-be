package com.isa2023team64.pharmacydeliverybe.controller;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa2023team64.pharmacydeliverybe.dto.AppointmentRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.AppointmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.mapper.AppointmentDTOMapper;
import com.isa2023team64.pharmacydeliverybe.model.Appointment;
import com.isa2023team64.pharmacydeliverybe.service.AppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Appointment controller", description = "Appointment API")
@RestController
@RequestMapping(value = "api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Operation(summary = "Get all appointments", description = "Gets all appointments.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All appointments fetched successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = AppointmentResponseDTO.class))))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentResponseDTO>> getAll() {
        List<Appointment> appointments = appointmentService.findAll();
        List<AppointmentResponseDTO> dtos = appointments.stream().map(AppointmentDTOMapper::toResponseDTO).collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Operation(summary = "Register new appointment", description = "Registers new appointment.", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Appointment registered successfully.",
                     content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Bad request.",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponseDTO.class)))
    })
    @PostMapping(path = "/new",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentResponseDTO> create(@RequestBody AppointmentRequestDTO requestDTO) {
        try {
            Appointment appointment = AppointmentDTOMapper.fromRequestDTO(requestDTO);
            appointment = appointmentService.makeAppointment(appointment);
            AppointmentResponseDTO dto = AppointmentDTOMapper.toResponseDTO(appointment);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (JpaObjectRetrievalFailureException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } 
    }

    @Operation(summary = "Reserve an appointment", description = "Reserves an appointment.", method = "PATCH")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Appointment reserved successfully.",
                     content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/reserve/{id}")
    public ResponseEntity<Void> reserve(@PathVariable Integer id) {
        try {
            appointmentService.reserveAppointment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by-company-id/{id}")
    public ResponseEntity<Collection<AppointmentResponseDTO>> findByCompanyId(@PathVariable Integer id) {
        try {
            var appointments = appointmentService.findByCompanyId(id);
            var dtos = appointments.stream().map(AppointmentDTOMapper::toResponseDTO).collect(Collectors.toList());
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
