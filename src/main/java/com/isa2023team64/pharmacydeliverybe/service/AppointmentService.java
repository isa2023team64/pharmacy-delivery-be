package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import com.isa2023team64.pharmacydeliverybe.model.Appointment;

public interface AppointmentService {
    
    public List<Appointment> findAll();

    public Appointment findById(Integer id);

    public Appointment save(Appointment appointment);

    public Appointment update(Appointment appointment);

    public Appointment makeAppointment(Appointment appointment);

    public void reserveAppointment(Integer id);

}
