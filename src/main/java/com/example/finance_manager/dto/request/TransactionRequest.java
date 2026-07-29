package com.example.finance_manager.dto.request;

import com.example.finance_manager.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private String name;
    private BigDecimal amount;
    private LocalDate date;
    private Long categoryId;
    private Long accountId;
}
