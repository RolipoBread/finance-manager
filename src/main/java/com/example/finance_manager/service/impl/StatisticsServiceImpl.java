package com.example.finance_manager.service.impl;

import com.example.finance_manager.dto.response.CategoryAmountResponse;
import com.example.finance_manager.repository.TransactionRepository;
import com.example.finance_manager.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    TransactionRepository transactionRepository;

    @Override
    public List<CategoryAmountResponse> getExpensesByCategory(Long userId) {
        return transactionRepository.findExpensesByCategory(userId);
    }

    @Override
    public List<CategoryAmountResponse> getIncomeByCategory(Long userId) {
        return transactionRepository.findIncomeByCategory(userId);
    }
}
