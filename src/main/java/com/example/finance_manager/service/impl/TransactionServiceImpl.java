package com.example.finance_manager.service.impl;


import com.example.finance_manager.dto.request.TransactionRequest;
import com.example.finance_manager.dto.response.TransactionResponse;
import com.example.finance_manager.entity.Transaction;
import com.example.finance_manager.mapper.TransactionMapper;
import com.example.finance_manager.repository.CategoryRepository;
import com.example.finance_manager.repository.FinancialAccountRepository;
import com.example.finance_manager.repository.TransactionRepository;
import com.example.finance_manager.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    private final CategoryRepository categoryRepository;
    private final FinancialAccountRepository accountRepository;

    @Override
    public TransactionResponse create(TransactionRequest request) {
        Transaction transaction = mapper.toEntity(request);
        transaction.setCategory(
                categoryRepository.findById(request.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found"))
        );
        transaction.setAccount(
                accountRepository.findById(request.getAccountId())
                        .orElseThrow(() -> new RuntimeException("Account not found"))
        );
        return mapper.toResponse(repository.save(transaction));
    }

    @Override
    public List<TransactionResponse> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public Transaction getById(Long id) {
        return repository.findById(id).orElseThrow(() ->new RuntimeException("transaction not found"));
    }

    @Override
    public TransactionResponse getResponseById(Long id) {
        return mapper.toResponse(getById(id));
    }

    @Override // переделать
    public TransactionResponse update(Long id, TransactionRequest request) {
        Transaction transaction = getById(id);
        transaction.setName(request.getName());
        transaction.setAmount(request.getAmount());
        transaction.setDate(request.getDate());
        transaction.setCategory(
                categoryRepository.findById(request.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found")));
        transaction.setAccount(
                accountRepository.findById(request.getAccountId())
                        .orElseThrow(() -> new RuntimeException("Account not found"))
        );
        Transaction save = repository.save(transaction);
        return mapper.toResponse(save);
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = getById(id);
        repository.delete(transaction);
    }
}
