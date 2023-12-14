package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.Appointment;
import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.repository.AppointmentRepository;
import com.isa2023team64.pharmacydeliverybe.repository.CompanyAdministratorRepository;
import com.isa2023team64.pharmacydeliverybe.service.AppointmentService;
import com.isa2023team64.pharmacydeliverybe.util.TimeSpan;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CompanyAdministratorRepository companyAdministratorRepository;
    
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
        appointment.setId(null);
        appointment = appointmentRepository.save(appointment);
        appointment = appointmentRepository.findById(appointment.getId()).orElseThrow();
        return appointment;
    }

    @Override
    public Appointment update(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment makeAppointment(Appointment appointment) {
        if (appointmentIsInFuture(appointment)) {
            throw new IllegalArgumentException("Appointment must be in future");
        }

        var companyAdministrator = companyAdministratorRepository.findById(appointment.getCompanyAdministrator().getId()).orElseThrow();
        Collection<Appointment> appointments = findByCompany(companyAdministrator.getCompany());
        for (Appointment a : appointments) {
            if (appointmentsOverlap(appointment, a)) {
                throw new IllegalArgumentException("Appointments overlap");
            }
        }

        Appointment savedAppointment = this.save(appointment);
        return savedAppointment;
    }

    private Collection<Appointment> findByCompany(Company company) {
        var appointments = appointmentRepository.findAll();
        Collection<Appointment> filtered = new HashSet<>();
        for (var appointment : appointments) {
            if (appointment.getCompanyAdministrator().getCompany().getId() == company.getId()) {
                filtered.add(appointment);
            }
        }
        return filtered;
    }

    private boolean appointmentsOverlap(Appointment a1, Appointment a2) {
        TimeSpan ts1 = TimeSpan.of(a1.getStartDateTime(), a1.getDuration());
        TimeSpan ts2 = TimeSpan.of(a2.getStartDateTime(), a2.getDuration());
        return ts1.overlaps(ts2);
    }

    private boolean appointmentIsInFuture(Appointment appointment) {
        return appointment.getStartDateTime().isAfter(LocalDateTime.now());
    }
    
}
