package com.example.finance_manager.controller;

import com.example.finance_manager.dto.request.TransactionRequest;
import com.example.finance_manager.dto.response.TransactionResponse;
import com.example.finance_manager.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Transactions",
        description = "Operations for managing financial transactions"
)
@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @Operation(summary = "Get all users")
    @GetMapping
    public Page<TransactionResponse> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return service.getAll(PageRequest.of(page,size, Sort.by("date").descending()));
    }

    @Operation(summary = "Get all transactions")
    @GetMapping("/account/{accountId}")
    public Page<TransactionResponse> getByAccount(@PathVariable Long accountId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        return service.getByAccount(accountId,PageRequest.of(page,size, Sort.by("date").descending()));
    }

    @Operation(summary = "Get transaction by id")
    @GetMapping("/{id}")
    public TransactionResponse getById(@PathVariable Long id) {
        return service.getResponseById(id);
    }

    @Operation(summary = "Create transaction")
    @PostMapping
    public TransactionResponse create(@Valid @RequestBody TransactionRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Update transaction by id")
    @PutMapping("/{id}")
    public TransactionResponse update(@Valid @PathVariable Long id,
                                      @RequestBody TransactionRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Delete transaction by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}