package com.isa2023team64.pharmacydeliverybe.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDTO {
    
    private LocalDateTime startDateTime;
    private Integer duration;
    private Integer companyAdministratorId;
    private Integer companyId;

}
