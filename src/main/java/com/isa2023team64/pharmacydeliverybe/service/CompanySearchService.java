package com.isa2023team64.pharmacydeliverybe.service;

import com.isa2023team64.pharmacydeliverybe.dto.CompanyNoAdminDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanySearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.util.PagedResult;

public interface CompanySearchService {

    public PagedResult<CompanyNoAdminDTO> search(CompanySearchFilterDTO filter);
}
