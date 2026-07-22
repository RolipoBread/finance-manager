package com.example.finance_manager.dto.response;

import com.example.finance_manager.entity.Transaction;
import com.example.finance_manager.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialAccountResponse {
    public Long id;
    public String name;
    public Double balance;
    public User owner;
    public List<Transaction> transactions;
}
