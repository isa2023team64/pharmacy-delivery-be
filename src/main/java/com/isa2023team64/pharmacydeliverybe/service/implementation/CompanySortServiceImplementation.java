package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.service.CompanySortService;

@Service
public class CompanySortServiceImplementation implements CompanySortService {

    @Override
    public List<Company> sort(List<Company> companies, String criteria) {
        criteria = criteria != null ? criteria : "";
        switch (criteria) {
            case "Name(ascending)":
                return sortByAttribute(companies, Company::getName, true);
            case "Name(descending)":
                return sortByAttribute(companies, Company::getName, false);
            case "Rating(ascending)":
                return sortByAttribute(companies, Company::getAverageRating, true);
            case "Rating(descending)":
                return sortByAttribute(companies, Company::getAverageRating, false);
            default:
                return new ArrayList<>(companies);
        }
    }

    private <R extends Comparable<? super R>> List<Company> sortByAttribute(List<Company> companies, Function<Company, R> keyExtractor, boolean ascending) {
        List<Company> sortedCompanies = new ArrayList<>(companies);
        Collections.sort(sortedCompanies, Comparator.comparing(keyExtractor));

        if(!ascending) {
            Collections.reverse(sortedCompanies);
        }

        return sortedCompanies;
    }
}
