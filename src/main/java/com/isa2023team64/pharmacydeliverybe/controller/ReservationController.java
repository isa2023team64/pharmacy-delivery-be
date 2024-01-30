package com.isa2023team64.pharmacydeliverybe.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa2023team64.pharmacydeliverybe.dto.AppointmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.ExtraordinaryReservationRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegisteredUserResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegularReservationRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegularReservationResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.ReservationItemNoReservationResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.ReservationResponseDTO;
import com.isa2023team64.pharmacydeliverybe.mapper.AppointmentDTOMapper;
import com.isa2023team64.pharmacydeliverybe.model.Appointment;
import com.isa2023team64.pharmacydeliverybe.model.Reservation;
import com.isa2023team64.pharmacydeliverybe.service.AppointmentService;
import com.isa2023team64.pharmacydeliverybe.service.ReservationItemQRService;
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

    @Autowired
    private ReservationItemQRService reservationItemQRService;

    @Operation(summary = "Create a new reservation.", description = "Create a new reservation.", method = "Post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Reservation created successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = AppointmentResponseDTO.class))))
    })
    @PostMapping(value = "/regular", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegularReservationResponseDTO> createRegular(@RequestBody RegularReservationRequestDTO dto) {
        try {
            RegularReservationResponseDTO responseDTO = reservationService.create(dto.getUserId(), dto.getAppointmentId(), dto.getEquipmentIds(), dto.getEquipmentQuantities());
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
            dto.getAppointment().setCompanyAdministratorId(-1);
            Appointment appointment = AppointmentDTOMapper.fromRequestDTO(dto.getAppointment());
            appointment = appointmentService.makeExtraordinaryAppointment(appointment);
            RegularReservationResponseDTO responseDTO = reservationService.create(dto.getUserId(), appointment.getId(), dto.getEquipmentIds(), dto.getEquipmentQuantities());
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @Operation(summary = "Retrive all user reservation appointments.", description = "\"Retrive all user reservation appointments.", method = "Get")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Appointments retrived successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = AppointmentResponseDTO.class))))
    })
    @GetMapping(value = "/user-appointments/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentResponseDTO>> getUserAppointments(@PathVariable Integer id) {
        List<AppointmentResponseDTO> appointments = reservationService.findAllUserAppointments(id);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @Operation(summary = "Mark a reservation as taken", description = "Marks a reservation as taken.", method = "PATCH")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservation taken successfully.",
                     content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/mark-as-taken/{id}")
    public ResponseEntity<Void> markAsTaken(@PathVariable Integer id) {
        try {
            reservationService.markReservationAsTaken(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @Operation(summary = "Retrive all user reservations by company.", description = "Retrive all user reservations by company.", method = "Get")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Appointments retrived successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = AppointmentResponseDTO.class))))
    })
    @GetMapping(value = "/by-company/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationResponseDTO>> getReservationsByCompany(@PathVariable Integer id) {
        Collection<Reservation> reservations = reservationService.getPendingByCompanyId(id);

        Collection<ReservationResponseDTO> dtos = new ArrayList<>();
        for (var reservation : reservations) {
            dtos.add(new ReservationResponseDTO(reservation));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    
    @Operation(summary = "Retrive all users that reserved in company.", description = "Retrives all users that reserved in company.", method = "Get")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Users retrived successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = RegisteredUserResponseDTO.class))))
    })
    @GetMapping(value = "/users-that-reserved-by-company/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<RegisteredUserResponseDTO>> getUsersThatReserved(@PathVariable Integer id) {
        var users = reservationService.getUsersThanReserved();

        Collection<RegisteredUserResponseDTO> dtos = new ArrayList<>();
        for (var user : users) {
            dtos.add(new RegisteredUserResponseDTO(user));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    
    @Operation(summary = "Delete Reservation.", description = "Cancel an appointment and delete the associated reservation.", method = "Delete")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Reservation canceled successfully."),
        @ApiResponse(responseCode = "404", description = "Appointment or reservation not found."),
        @ApiResponse(responseCode = "400", description = "Invalid request.")
    })
    @DeleteMapping(value = "/deleteReservation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> cancelExtraordinary(@PathVariable int id) {
        try {
            // Call the delete function to cancel the appointment and associated reservation
            reservationService.deleteReservation(id);
            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Retrive reservation items by reservation id.", description = "Retrives reservation items by reservation id.", method = "Get")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Reservation items retrived successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = RegisteredUserResponseDTO.class))))
    })
    @GetMapping(value = "/reservation-items-by-reservation-id/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationItemNoReservationResponseDTO>> getReservationItemsByReservationId(@PathVariable Integer id) {
        var reservationItems = reservationItemQRService.findByReservationId(id);

        Collection<ReservationItemNoReservationResponseDTO> reservationItemsDtos = new ArrayList<>();
        for (var reservationItem : reservationItems) {
            reservationItemsDtos.add(new ReservationItemNoReservationResponseDTO(reservationItem));
        }

        return new ResponseEntity<>(reservationItemsDtos, HttpStatus.OK);
    }

    @Operation(summary = "Retrieve a reservation by ID", description = "Retrieve a reservation by its ID.", method = "Get")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservation retrieved successfully.",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = ReservationResponseDTO.class)))),
        @ApiResponse(responseCode = "404", description = "Reservation not found.")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Integer id) {
        try {
            Reservation reservation = reservationService.findById(id);
            ReservationResponseDTO dto = new ReservationResponseDTO(reservation);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
