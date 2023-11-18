package com.isa2023team64.pharmacydeliverybe.util.converters;

import java.util.List;

import com.isa2023team64.pharmacydeliverybe.util.PagedResult;

public interface PagedResultConverter {

    public <T> PagedResult<T> convertToPagedResult(List<T> list, int page, int pageSize);
}
