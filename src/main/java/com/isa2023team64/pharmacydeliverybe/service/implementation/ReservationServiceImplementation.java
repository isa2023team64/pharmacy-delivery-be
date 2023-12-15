package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.dto.AppointmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanyNoAdminDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegisteredUserResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegularReservationRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegularReservationResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.Appointment;
import com.isa2023team64.pharmacydeliverybe.model.Equipment;
import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;
import com.isa2023team64.pharmacydeliverybe.model.Reservation;
import com.isa2023team64.pharmacydeliverybe.repository.AppointmentRepository;
import com.isa2023team64.pharmacydeliverybe.repository.EquipmentRepository;
import com.isa2023team64.pharmacydeliverybe.repository.RegisteredUserRepository;
import com.isa2023team64.pharmacydeliverybe.repository.ReservationRepository;
import com.isa2023team64.pharmacydeliverybe.service.ReservationService;

@Service
public class ReservationServiceImplementation implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private RegisteredUserRepository userRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public RegularReservationResponseDTO createRegular(RegularReservationRequestDTO dto) {
        RegisteredUser user = userRepository.findById(dto.getUserId());

        List<Equipment> equipmentList = new ArrayList<>();
        for (int id : dto.getEquipmentIds()) {
            Equipment equipment = equipmentRepository.findById(id).orElseThrow();
            if (equipment.getStockCount() <= 0)
                throw new NoSuchElementException();
            //equipment.setStockCount(equipment.getStockCount() - 1);
            equipmentList.add(equipment);
        }

        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId()).orElseThrow();

        Reservation reservation = new Reservation(false, false, false, appointment, user, equipmentList);
        reservation = reservationRepository.save(reservation);

        RegisteredUserResponseDTO userDTO = modelMapper.map(user, RegisteredUserResponseDTO.class);
        AppointmentResponseDTO appointmentDTO = modelMapper.map(appointment, AppointmentResponseDTO.class);
        List<EquipmentResponseDTO> equipmentDTO = equipmentList.stream()
                .map(equipment -> modelMapper.map(equipment, EquipmentResponseDTO.class))
                .collect(Collectors.toList());
        RegularReservationResponseDTO reservationDTO = new RegularReservationResponseDTO(reservation, appointmentDTO, userDTO, equipmentDTO);
        return reservationDTO;
    }


    
}
