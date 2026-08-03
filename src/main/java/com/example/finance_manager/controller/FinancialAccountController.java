package com.example.finance_manager.controller;

import com.example.finance_manager.dto.request.FinancialAccountRequest;
import com.example.finance_manager.dto.response.FinancialAccountResponse;
import com.example.finance_manager.service.FinancialAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Financial Accounts",
        description = "Operations for user financial accounts"
)
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class FinancialAccountController {

    private final FinancialAccountService service;

    @Operation(summary = "Get all accounts")
    @GetMapping
    public List<FinancialAccountResponse> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get account by id")
    @GetMapping("/{id}")
    public FinancialAccountResponse getById(@PathVariable Long id) {
        return service.getResponseById(id);
    }

    @Operation(summary = "Create account")
    @PostMapping
    public FinancialAccountResponse create(@Valid @RequestBody FinancialAccountRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Update account by id")
    @PutMapping("/{id}")
    public FinancialAccountResponse update(@Valid @PathVariable Long id,
                                           @RequestBody FinancialAccountRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Delete account by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}