package com.example.finance_manager.dto.filter;

import com.example.finance_manager.entity.TransactionPeriod;
import com.example.finance_manager.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionFilter {
    private Long userId;
    private Long accountId;
    private TransactionType type;
    private TransactionPeriod period;
    private LocalDate from;
    private LocalDate to;
}
