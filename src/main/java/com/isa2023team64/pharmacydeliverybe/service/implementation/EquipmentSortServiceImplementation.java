package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.Equipment;
import com.isa2023team64.pharmacydeliverybe.service.EquipmentSortService;

@Service
public class EquipmentSortServiceImplementation implements EquipmentSortService {

    public List<Equipment> sort(List<Equipment> equipment, String criteria) {
        criteria = criteria != null ? criteria : "";
        switch (criteria) {
            case "Name(ascending)":
                return sortByAttribute(equipment, Equipment::getName, true);
            case "Name(descending)":
                return sortByAttribute(equipment, Equipment::getName, false);
            case "Rating(ascending)":
                return sortByAttribute(equipment, Equipment::getAverageRating, true);
            case "Rating(descending)":
                return sortByAttribute(equipment, Equipment::getAverageRating, false);
            default:
                return new ArrayList<>(equipment);
        }
    }

    private <R extends Comparable<? super R>> List<Equipment> sortByAttribute(List<Equipment> equipment, Function<Equipment, R> keyExtractor, boolean ascending) {
        List<Equipment> sortedEquipment = new ArrayList<>(equipment);
        Collections.sort(sortedEquipment, Comparator.comparing(keyExtractor));

        if(!ascending) {
            Collections.reverse(sortedEquipment);
        }

        return sortedEquipment;
    }
}
