package com.example.finance_manager.service;

import com.example.finance_manager.dto.filter.DateRange;
import com.example.finance_manager.dto.filter.TransactionFilter;

public interface DateRangeResolver {
    DateRange resolve(TransactionFilter filter);

}
