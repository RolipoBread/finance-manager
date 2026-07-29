package com.example.finance_manager.controller;

import com.example.finance_manager.dto.request.FinancialAccountRequest;
import com.example.finance_manager.dto.response.FinancialAccountResponse;
import com.example.finance_manager.service.FinancialAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class FinancialAccountController {

    private final FinancialAccountService service;

    @GetMapping
    public List<FinancialAccountResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public FinancialAccountResponse getById(@PathVariable Long id) {
        return service.getResponseById(id);
    }

    @PostMapping
    public FinancialAccountResponse create(@RequestBody FinancialAccountRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public FinancialAccountResponse update(@PathVariable Long id,
                                           @RequestBody FinancialAccountRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}