package com.isa2023team64.pharmacydeliverybe.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa2023team64.pharmacydeliverybe.dto.AppointmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.ExtraordinaryReservationRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegularReservationRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegularReservationResponseDTO;
import com.isa2023team64.pharmacydeliverybe.mapper.AppointmentDTOMapper;
import com.isa2023team64.pharmacydeliverybe.model.Appointment;
import com.isa2023team64.pharmacydeliverybe.service.AppointmentService;
import com.isa2023team64.pharmacydeliverybe.service.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Reservation controller", description = "Reservation API")
@RestController
@RequestMapping(value = "api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AppointmentService appointmentService;

    @Operation(summary = "Create a new reservation.", description = "Create a new reservation.", method = "Post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Reservation created successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = AppointmentResponseDTO.class))))
    })
    @PostMapping(value = "/regular", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegularReservationResponseDTO> createRegular(@RequestBody RegularReservationRequestDTO dto) {
        try {
            RegularReservationResponseDTO responseDTO = reservationService.create(dto.getUserId(), dto.getAppointmentId(), dto.getEquipmentIds());
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Create a new reservation.", description = "Create a new reservation.", method = "Post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Reservation created successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = AppointmentResponseDTO.class))))
    })
    @PostMapping(value = "/extraordinary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegularReservationResponseDTO> createExtraordinary(@RequestBody ExtraordinaryReservationRequestDTO dto) {
        try {
            dto.getAppointment().setCompanyAdministratorFullName("Full Name");
            Appointment appointment = AppointmentDTOMapper.fromRequestDTO(dto.getAppointment());
            appointment = appointmentService.makeExtraordinaryAppointment(appointment);
            RegularReservationResponseDTO responseDTO = reservationService.create(dto.getUserId(), appointment.getId(), dto.getEquipmentIds());
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
