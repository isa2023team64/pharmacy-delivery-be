package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;
import com.isa2023team64.pharmacydeliverybe.model.EquipmentSearchFilter;
import com.isa2023team64.pharmacydeliverybe.service.EquipmentFilterService;

@Service
public class EquipmentFilterServiceImplementation implements EquipmentFilterService {

    public List<Equipment> filter(List<Equipment> equipments, EquipmentSearchFilter filter) {
        List<Equipment> filteredEquipments = equipments.stream()
                .filter(equipment -> matchesFilter(equipment, filter))
                .collect(Collectors.toList());

        return filteredEquipments;
    }

    private boolean matchesFilter(Equipment equipment, EquipmentSearchFilter filter) {
        boolean matchesFilter = 
            matchesNameFilter(equipment, filter.getName()) &&
            matchesTypeFilter(equipment, filter.getType()) &&
            matchesDescriptionFilter(equipment, filter.getDescription()) &&
            matchesMinRatingFilter(equipment, filter.getMinRating()) &&
            matchesMaxRatingFilter(equipment, filter.getMaxRating());

        return matchesFilter;
    }

    private boolean matchesNameFilter(Equipment equipment, String nameFilter) {
        return equipment.getName().toLowerCase().contains(nameFilter.toLowerCase());
    }

    private boolean matchesTypeFilter(Equipment equipment, String typeFilter) {
        return equipment.getType().toLowerCase().contains(typeFilter.toLowerCase());
    }

    private boolean matchesDescriptionFilter(Equipment equipment, String descriptionFilter) {
        return equipment.getDescription().toLowerCase().contains(descriptionFilter.toLowerCase());
    }

    private boolean matchesMinRatingFilter(Equipment equipment, double minRating) {
        return equipment.getAverageRating() >= minRating;
    }

    private boolean matchesMaxRatingFilter(Equipment equipment, double maxRating) {
        return equipment.getAverageRating() <= maxRating;
    }
}
