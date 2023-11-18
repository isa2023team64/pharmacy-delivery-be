package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import com.isa2023team64.pharmacydeliverybe.model.Company;

public interface CompanySortService {

    public List<Company> sort(List<Company> companies, String criteria);
}
