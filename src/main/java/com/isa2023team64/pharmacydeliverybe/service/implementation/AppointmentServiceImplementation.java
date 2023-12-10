package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.Appointment;
import com.isa2023team64.pharmacydeliverybe.repository.AppointmentRepository;
import com.isa2023team64.pharmacydeliverybe.service.AppointmentService;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment findById(Integer id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment add(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
    
}
