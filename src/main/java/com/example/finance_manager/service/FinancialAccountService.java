package com.example.finance_manager.service;

import com.example.finance_manager.dto.request.FinancialAccountRequest;
import com.example.finance_manager.dto.response.FinancialAccountResponse;
import com.example.finance_manager.entity.FinancialAccount;

import java.util.List;

public interface FinancialAccountService {
    FinancialAccountResponse create(FinancialAccountRequest request);
    List<FinancialAccountResponse> getAll();
    FinancialAccount getById(Long id);
    FinancialAccountResponse getResponseById(Long id);
    FinancialAccountResponse update(Long id, FinancialAccountRequest request);
    void delete(Long id);
}