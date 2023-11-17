package com.isa2023team64.pharmacydeliverybe.util.converters.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import com.isa2023team64.pharmacydeliverybe.util.PagedResult;
import com.isa2023team64.pharmacydeliverybe.util.converters.PagedResultConverter;

@Component
public class PagedResultConverterImplementation implements PagedResultConverter {

    public <T> PagedResult<T> convertToPagedResult(List<T> list, int page, int pageSize) {
        if (page < 0 || pageSize < 0) {
            throw new IllegalArgumentException("Invalid page number or page size");
        }

        if (page == 0 && pageSize == 0) {
            return new PagedResult<T>(list, list.size());
        }

        int totalCount = list.size();

        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalCount);

        List<T> pageList = fromIndex < totalCount ? list.subList(fromIndex, toIndex) : List.of();

        return new PagedResult<T>(pageList, totalCount);
    }
}
