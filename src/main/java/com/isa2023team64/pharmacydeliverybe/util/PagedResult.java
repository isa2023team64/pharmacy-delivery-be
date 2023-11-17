package com.isa2023team64.pharmacydeliverybe.util;

import java.util.List;

public class PagedResult<T> {
    private List<T> results;
    private int totalCount;

    public PagedResult(List<T> items, int totalCount) {
        this.totalCount = totalCount;
        this.results = items;
    }

    public List<T> getResults() {
        return results;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
