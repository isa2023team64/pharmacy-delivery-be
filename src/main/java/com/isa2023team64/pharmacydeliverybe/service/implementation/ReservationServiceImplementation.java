package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.controller.RegisteredUserController;
import com.isa2023team64.pharmacydeliverybe.dto.AppointmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegisteredUserResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.RegularReservationResponseDTO;
import com.isa2023team64.pharmacydeliverybe.model.Appointment;
import com.isa2023team64.pharmacydeliverybe.model.Equipment;
import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;
import com.isa2023team64.pharmacydeliverybe.model.Reservation;
import com.isa2023team64.pharmacydeliverybe.repository.AppointmentRepository;
import com.isa2023team64.pharmacydeliverybe.repository.EquipmentRepository;
import com.isa2023team64.pharmacydeliverybe.repository.RegisteredUserRepository;
import com.isa2023team64.pharmacydeliverybe.repository.ReservationRepository;
import com.isa2023team64.pharmacydeliverybe.service.EmailService;
import com.isa2023team64.pharmacydeliverybe.service.QRCodeGenerator;
import com.isa2023team64.pharmacydeliverybe.service.ReservationService;
import com.isa2023team64.pharmacydeliverybe.util.enums.AppointmentStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

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

    @PersistenceContext
    private EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(RegisteredUserController.class);

	@Autowired
	private EmailService emailService;

    @Transactional
    public RegularReservationResponseDTO create(int userId, int appointmentId, List<Integer> equipmentIds) {
        RegisteredUser user = userRepository.findById(userId);

        List<Equipment> equipmentList = new ArrayList<>();
        for (int id : equipmentIds) {
            Equipment equipment = equipmentRepository.findById(id).orElseThrow();
            if (equipment.getStockCount() <= 0)
                throw new NoSuchElementException();
            equipment.setStockCount(equipment.getStockCount() - 1);
            equipmentList.add(equipment);
        }

        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        appointment.setStatus(AppointmentStatus.RESERVED);

        Reservation reservation = new Reservation(false, false, false, appointment, user, equipmentList);
        user = entityManager.merge(user);
        equipmentList = equipmentList.stream()
            .map(equipment -> entityManager.merge(equipment))
            .collect(Collectors.toList());
        appointment = entityManager.merge(appointment);
        reservation = reservationRepository.save(reservation);

        RegisteredUserResponseDTO userDTO = modelMapper.map(user, RegisteredUserResponseDTO.class);
        AppointmentResponseDTO appointmentDTO = modelMapper.map(appointment, AppointmentResponseDTO.class);
        List<EquipmentResponseDTO> equipmentDTO = equipmentList.stream()
                .map(equipment -> modelMapper.map(equipment, EquipmentResponseDTO.class))
                .collect(Collectors.toList());
        RegularReservationResponseDTO reservationDTO = new RegularReservationResponseDTO(reservation, appointmentDTO, userDTO, equipmentDTO);

        String reservationInfo = generateReservationInfo(reservation);
        
        try {
			System.out.println("Thread id for reservation info sending: " + Thread.currentThread().getId());
            byte[] qrCodeBytes = QRCodeGenerator.generateQRCode(reservationInfo);
			emailService.sendReservationInfoAsync(user,qrCodeBytes);
		}catch( Exception e ){
			logger.info("Greska prilikom slanja emaila za rezervaciju: " + e.getMessage());
		}

        return reservationDTO;
    }

    private String generateReservationInfo(Reservation reservation) {
        StringBuilder infoBuilder = new StringBuilder();
        
        infoBuilder.append("RESERVATION INFORMATION:\n");
        infoBuilder.append("Id: ").append(reservation.getId()).append("\n");
        infoBuilder.append("Customer: ").append(reservation.getUser().getFullName()).append("\n");
        infoBuilder.append("Order Items:\n");
        
        for (Equipment equipment : reservation.getOrderItems()) {
            infoBuilder.append(equipment.getName()).append("\n");
        }
        
        infoBuilder.append("Appointment: ").append(reservation.getAppointment().getStartDateTime()).append("\n");
        infoBuilder.append("Appointment duration: ").append(reservation.getAppointment().getDuration()).append("min").append("\n");
    
        return infoBuilder.toString();
    }
    
    @Override
    public List<Appointment> findAllUserAppointments(int userId) {

        //dobavim sve rezervacije od usera sa tim id i onda sve te appointmente od tih rezervacija dodam u novu listu
        List<Appointment> appointments = new ArrayList<>();

        List<Reservation> userReservations = reservationRepository.findAll().stream()
        .filter(reservation -> reservation.getUser().getId() ==  userId)
        .collect(Collectors.toList());

        for (Reservation reservation : userReservations) {
            appointments.add(reservation.getAppointment());
        }        

        return appointments;
    }
}
