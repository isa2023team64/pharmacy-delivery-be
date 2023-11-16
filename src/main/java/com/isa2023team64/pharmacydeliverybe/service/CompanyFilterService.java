package com.isa2023team64.pharmacydeliverybe.service;

import java.util.ArrayList;

import com.isa2023team64.pharmacydeliverybe.model.CompanySearchFilter;

public interface CompanyFilterService {

    public void filter(ArrayList<String> companies, CompanySearchFilter filter);
}

