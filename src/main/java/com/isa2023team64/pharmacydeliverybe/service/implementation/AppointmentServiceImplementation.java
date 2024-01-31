package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.isa2023team64.pharmacydeliverybe.model.Appointment;
import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;
import com.isa2023team64.pharmacydeliverybe.repository.AppointmentRepository;
import com.isa2023team64.pharmacydeliverybe.repository.CompanyAdministratorRepository;
import com.isa2023team64.pharmacydeliverybe.repository.CompanyRepository;
import com.isa2023team64.pharmacydeliverybe.service.AppointmentService;
import com.isa2023team64.pharmacydeliverybe.util.TimeSpan;
import com.isa2023team64.pharmacydeliverybe.util.WorkingHours;
import com.isa2023team64.pharmacydeliverybe.util.enums.AppointmentStatus;


@Service
public class AppointmentServiceImplementation implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

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
        return makeAppointment(appointment, AppointmentStatus.FREE);
    }
    
    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public Appointment makeAppointment(Appointment appointment, AppointmentStatus status) {
        
        if (!isAppointmentInFuture(appointment)) {
            throw new IllegalArgumentException("Appointment must be in future");
        }
        
        var company = companyRepository.findById(appointment.getCompany().getId()).orElseThrow();
        
        if (!isAppointmentInWorkingHours(appointment, company)) {
            throw new IllegalArgumentException("Appointment must be in company working hours");
        }
        
        Collection<Appointment> appointments = findByCompany(company);
        for (Appointment a : appointments) {
            if (appointmentsOverlap(appointment, a)) {
                throw new IllegalArgumentException("Appointments overlap");
            }
        }
        
        appointment.setStatus(AppointmentStatus.FREE);
        Appointment savedAppointment = this.save(appointment);
        return savedAppointment;
    }
    
    
    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public Appointment makeExtraordinaryAppointment(Appointment appointment)  {
        Company company = companyRepository.findById(appointment.getCompany().getId()).orElseThrow();
        // List<CompanyAdministrator> admins = companyAdministratorRepository.findAll();
        // CompanyAdministrator admin = admins.get(0);
        // for (var a : admins) {
        //     if(a.getCompany().getId() == company.getId()) {
        //         admin = a;
        //         break;
        //     }
        // }

        //Calls Repo Method with Lock

        List<CompanyAdministrator> admins = companyAdministratorRepository
                .findAvailableAdministratorsForCompany(company.getId());
    
        if (admins.isEmpty()) {
            throw new IllegalStateException("No available company administrators found.");
        }
    
        // For simplicity, you can choose the first available administrator.
        CompanyAdministrator admin = admins.get(0);



        appointment.setCompanyAdministrator(admin);

        return makeAppointment(appointment, AppointmentStatus.RESERVED);
    }

    private Collection<Appointment> findByCompany(Company company) {
        var appointments = appointmentRepository.findAll();
        Collection<Appointment> filtered = new HashSet<>();
        for (var appointment : appointments) {
            if (appointment.getCompany().getId() == company.getId()) {
                filtered.add(appointment);
            }
        }
        return filtered;
    }

    private boolean appointmentsOverlap(Appointment a1, Appointment a2) {
        TimeSpan ts1 = TimeSpan.of(a1.getStartDateTime(), a1.getDuration());
        TimeSpan ts2 = TimeSpan.of(a2.getStartDateTime(), a2.getDuration());
        boolean overlap = ts1.overlaps(ts2);
        if (overlap) {
            System.out.println("Overlaping appointments.");
        }
        return ts1.overlaps(ts2);
    }
    
    private boolean isAppointmentInFuture(Appointment appointment) {
        return appointment.getStartDateTime().isAfter(LocalDateTime.now());
    }
    
    private boolean isAppointmentInWorkingHours(Appointment appointment, Company company) {
        WorkingHours workingHours = WorkingHours.of(company.getOpeningTime(), company.getClosingTime());
        return workingHours.isInside(appointment.getStartDateTime().toLocalTime()) && workingHours.isInside(appointment.getEndTime().toLocalTime());
    }

    @Override
    @jakarta.transaction.Transactional
    public void reserveAppointment(Integer id) {
        var appointment = appointmentRepository.findById(id).orElseThrow();
        if (appointment.getStatus() == AppointmentStatus.RESERVED) throw new IllegalArgumentException("Appointment already reserved.");
        appointment.setStatus(AppointmentStatus.RESERVED);
        appointmentRepository.save(appointment);
    }

    @Override
    public Collection<Appointment> findByCompanyId(Integer companyId) {
        var company = companyRepository.findById(companyId).orElseThrow();
        var appointments = appointmentRepository.findByCompany(company);
        return appointments;
    }

    @Override
    public void cancleAppointment(Integer id) {
        var appointment = appointmentRepository.findById(id).orElseThrow();
        if (appointment.getStatus() == AppointmentStatus.FREE) throw new IllegalArgumentException("Appointment already reserved.");
        appointment.setStatus(AppointmentStatus.FREE);
        appointmentRepository.save(appointment);
    }  

    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId parisZone = ZoneId.of("Europe/Paris");
        return Date.from(localDateTime.atZone(parisZone).toInstant());
    }
}
