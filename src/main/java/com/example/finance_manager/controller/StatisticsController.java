package com.example.finance_manager.controller;


import com.example.finance_manager.dto.response.CategoryAmountResponse;
import com.example.finance_manager.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService service;
    @GetMapping("/categories/expenses/{userId}")
    public List<CategoryAmountResponse> getExpensesByCategory(@PathVariable Long userId){return service.getExpensesByCategory(userId);}
    @GetMapping("/categories/income/{userId}")
    public List<CategoryAmountResponse> getIncomeByCategory(@PathVariable Long userId){return service.getIncomeByCategory(userId);}
}
