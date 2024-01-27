package com.isa2023team64.pharmacydeliverybe.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isa2023team64.pharmacydeliverybe.dto.AppointmentRequestDTO;
import com.isa2023team64.pharmacydeliverybe.dto.AppointmentResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.ExtraordinaryReservationRequestDTO;
import com.isa2023team64.pharmacydeliverybe.model.Appointment;
import com.isa2023team64.pharmacydeliverybe.model.GenericEntity;

@Component
public class AppointmentDTOMapper {
    
    private static ModelMapper mapper;

    @Autowired
    public AppointmentDTOMapper(ModelMapper mapper) {
        super();
        AppointmentDTOMapper.mapper = mapper;
    }

    public static Appointment fromRequestDTO(AppointmentRequestDTO dto) {
        return mapper.map(dto, Appointment.class);
    }

    public static Appointment fromRequestDTO(ExtraordinaryReservationRequestDTO dto) {
        TypeMap<ExtraordinaryReservationRequestDTO, Appointment> propertyMapper = mapper.createTypeMap(ExtraordinaryReservationRequestDTO.class, Appointment.class);
        propertyMapper.addMappings(mapper -> mapper.skip(GenericEntity::setId));
        return mapper.map(dto, Appointment.class);
    }
    
    public static AppointmentResponseDTO toResponseDTO(Appointment appointment) {
        return mapper.map(appointment, AppointmentResponseDTO.class);
    }

}
