package com.example.finance_manager.entity;

import java.time.LocalDate;

public class TransactionFilter {
    private Long userId;
    private Long accountId;
    private TransactionType type;
    private TransactionPeriod period;
    private LocalDate from;
    private LocalDate to;
}
