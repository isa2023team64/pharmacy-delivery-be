package com.isa2023team64.pharmacydeliverybe.dto;

import java.time.LocalDateTime;

import com.isa2023team64.pharmacydeliverybe.util.enums.AppointmentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDTO {
    
    private Integer id;
    private LocalDateTime startDateTime;
    private Integer duration;
    private AppointmentStatus status;
    private String companyAdministratorFullName;
    private CompanyInfoResponseDTO company;

}
