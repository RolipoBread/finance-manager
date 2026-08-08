package com.example.finance_manager.service.impl;

import com.example.finance_manager.dto.filter.DateRange;
import com.example.finance_manager.dto.filter.TransactionFilter;
import com.example.finance_manager.entity.TransactionPeriod;
import com.example.finance_manager.service.DateRangeResolver;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateRangeResolverImpl implements DateRangeResolver {

    @Override
    public DateRange resolve(TransactionFilter filter) {
        if (filter.getFrom() != null && filter.getTo() != null) return new DateRange(filter.getFrom(), filter.getTo());

        LocalDate now = LocalDate.now();
        TransactionPeriod period = filter.getPeriod();

        if (period == null) {
            return null;
        }

        return switch (period) {
            case DAY -> new DateRange(now, now);
            case WEEK -> new DateRange(now.minusDays(7), now);
            case MONTH -> new DateRange(now.withDayOfMonth(1), now);
        };
    }
}