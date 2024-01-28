package com.isa2023team64.pharmacydeliverybe.model;

import java.time.LocalDateTime;

import com.isa2023team64.pharmacydeliverybe.util.enums.AppointmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment extends GenericEntity {
    
    @Column
    @NotNull
    private LocalDateTime startDateTime;
    
    @Column
    @NotNull
    private Integer duration; // minutes

    @Enumerated
    private AppointmentStatus status;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    private CompanyAdministrator companyAdministrator;

    public LocalDateTime getEndTime() {
        return startDateTime.plusMinutes(duration);
    }

}
