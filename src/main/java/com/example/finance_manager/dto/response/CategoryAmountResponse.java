package com.example.finance_manager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CategoryAmountResponse {
    private String categoryName;
    private BigDecimal totalAmount;
}
