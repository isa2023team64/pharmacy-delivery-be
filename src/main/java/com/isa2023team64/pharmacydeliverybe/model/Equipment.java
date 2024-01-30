package com.isa2023team64.pharmacydeliverybe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
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
public class Equipment extends GenericEntity {
    
    @Column(unique = false, nullable = false)
    @NotEmpty
    private String name;

    @Column
    @NotNull
    private String description;

    @Column
    @NotNull
    private String type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Company company;
    
    @Column
    @NotNull
    private Integer stockCount;

    @Column
    @NotNull
    private double averageRating;

}
