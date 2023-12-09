package com.isa2023team64.pharmacydeliverybe.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Equipment extends GenericEntity {
    
    @Column(unique = true, nullable = false)
    @NotEmpty
    private String name;

    @Column
    @NotNull
    private String description;

    @Column
    @NotNull
    private String type;

    @Column
    @NotNull
    private double averageRating;

	@ManyToMany( fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	@JoinTable(name = "company_uses_equipment", joinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"))
    private List<Company> companies;

    public Equipment(Integer id, String name, String description, String type, double averageRating) {
        super(id);
        this.name = name;
        this.description = description;
        this.type = type;
        this.averageRating = averageRating;
    }

}
