package com.example.finance_manager.service;

import com.example.finance_manager.dto.response.CategoryAmountResponse;

import java.util.List;

public interface StatisticsService {
    List<CategoryAmountResponse> getExpensesByCategory(Long userId);
    List<CategoryAmountResponse> getIncomeByCategory(Long userId);
}
