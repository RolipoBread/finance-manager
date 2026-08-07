package com.example.finance_manager.service;

import com.example.finance_manager.entity.DateRange;
import com.example.finance_manager.entity.TransactionFilter;

public interface DateRangeResolver {
    DateRange resolve(TransactionFilter filter);

}
