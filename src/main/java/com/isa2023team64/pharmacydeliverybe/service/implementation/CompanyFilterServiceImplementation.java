package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.model.CompanySearchFilter;
import com.isa2023team64.pharmacydeliverybe.service.CompanyFilterService;

@Service
public class CompanyFilterServiceImplementation implements CompanyFilterService {

    @Override
    public List<Company> filter(List<Company> companies, CompanySearchFilter filter) {
        List<Company> filteredCompanies = companies.stream()
                .filter(company -> matchesFilter(company, filter))
                .collect(Collectors.toList());

        return filteredCompanies;
    }

    private boolean matchesFilter(Company company, CompanySearchFilter filter) {
        boolean matchesFilter = 
            matchesNameFilter(company, filter.getName()) &&
            matchesCountryFilter(company, filter.getCountry()) &&
            matchesCityFilter(company, filter.getCity()) &&
            matchesMinRatingFilter(company, filter.getMinRating()) &&
            matchesMaxRatingFilter(company, filter.getMaxRating());

        return matchesFilter;
    }

    private boolean matchesNameFilter(Company company, String nameFilter) {
        return company.getName().toLowerCase().contains(nameFilter.toLowerCase());
    }

    private boolean matchesCountryFilter(Company company, String countryFilter) {
        return company.getCountry().toLowerCase().contains(countryFilter.toLowerCase());
    }

    private boolean matchesCityFilter(Company company, String cityFilter) {
        return company.getCity().toLowerCase().contains(cityFilter.toLowerCase());
    }

    private boolean matchesMinRatingFilter(Company company, double minRating) {
        return company.getAverageRating() >= minRating;
    }

    private boolean matchesMaxRatingFilter(Company company, double maxRating) {
        return company.getAverageRating() <= maxRating;
    }
}
