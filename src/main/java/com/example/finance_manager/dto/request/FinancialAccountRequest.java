package com.example.finance_manager.dto.request;

import com.example.finance_manager.entity.Transaction;
import com.example.finance_manager.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialAccountRequest {
    private String name;
    private BigDecimal balance;
    private Long ownerId;
}
