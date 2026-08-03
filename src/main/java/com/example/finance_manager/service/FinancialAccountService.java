package com.example.finance_manager.service;

import com.example.finance_manager.dto.request.FinancialAccountRequest;
import com.example.finance_manager.dto.response.FinancialAccountResponse;
import com.example.finance_manager.entity.FinancialAccount;

import java.math.BigDecimal;
import java.util.List;

public interface FinancialAccountService {
    void updateBalance(Long id, BigDecimal amount);
    FinancialAccountResponse create(FinancialAccountRequest request);
    List<FinancialAccountResponse> getAll();
    FinancialAccount getById(Long id);
    FinancialAccount getByIdWithTransactions(Long id);
    FinancialAccountResponse getResponseById(Long id);
    FinancialAccountResponse update(Long id, FinancialAccountRequest request);
    void delete(Long id);
}