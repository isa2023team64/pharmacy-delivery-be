package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.model.CompanySearchFilter;

public interface CompanyFilterService {

    public List<Company> filter(List<Company> companies, CompanySearchFilter filter);
}

