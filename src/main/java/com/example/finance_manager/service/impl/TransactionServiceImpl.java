package com.example.finance_manager.service.impl;

import com.example.finance_manager.dto.request.TransactionRequest;
import com.example.finance_manager.dto.response.TransactionResponse;
import com.example.finance_manager.entity.FinancialAccount;
import com.example.finance_manager.entity.Transaction;
import com.example.finance_manager.entity.TransactionPeriod;
import com.example.finance_manager.mapper.TransactionMapper;
import com.example.finance_manager.repository.TransactionRepository;
import com.example.finance_manager.service.CategoryService;
import com.example.finance_manager.service.FinancialAccountService;
import com.example.finance_manager.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    private final CategoryService categoryService;
    private final FinancialAccountService accountService;

    @Override
    @Transactional
    public TransactionResponse create(TransactionRequest request) {

        Transaction transaction = mapper.toEntity(request);
        transaction.setCategory(categoryService.getById(request.getCategoryId()));
        FinancialAccount account = accountService.getById(request.getAccountId());
        transaction.setAccount(account);

        Transaction saved = repository.save(transaction);
        accountService.updateBalance(account.getId(), request.getAmount());
        return mapper.toResponse(saved);
    }


    @Override
    public Page<TransactionResponse> getAll(TransactionPeriod period, LocalDate from, LocalDate to, Pageable pageable){
        if(from != null && to != null) return repository.findByDateBetween(from,to,pageable).map(mapper::toResponse);

        if(period != null){
            LocalDate now = LocalDate.now();
            switch(period){
                case DAY -> {
                    return repository.findByDateBetween(now, now, pageable).map(mapper::toResponse);
                }
                case WEEK -> {
                    return repository.findByDateBetween(now.minusDays(7), now, pageable).map(mapper::toResponse);
                }
                case MONTH -> {
                    return repository.findByDateBetween(now.withDayOfMonth(1), now, pageable).map(mapper::toResponse);
                }
            }
        }
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public  Page<TransactionResponse> getByAccount(Long accoingId, Pageable pageable){
        accountService.getById(accoingId);
        return repository.findByAccountId(accoingId,pageable).map(mapper::toResponse);
    }


    @Override
    public Transaction getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }


    @Override
    public TransactionResponse getResponseById(Long id) {
        return mapper.toResponse(getById(id));
    }


    @Override
    @Transactional
    public TransactionResponse update(Long id, TransactionRequest request) {

        Transaction transaction = getById(id);
        FinancialAccount oldAccount = transaction.getAccount();

        accountService.updateBalance(oldAccount.getId(), transaction.getAmount().negate());
        transaction.setName(request.getName());
        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setDate(request.getDate());
        transaction.setCategory(categoryService.getById(request.getCategoryId()));
        FinancialAccount newAccount = accountService.getById(request.getAccountId());
        transaction.setAccount(newAccount);
        Transaction saved = repository.save(transaction);
        accountService.updateBalance(newAccount.getId(), request.getAmount());
        return mapper.toResponse(saved);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        Transaction transaction = getById(id);
        accountService.updateBalance(transaction.getAccount().getId(), transaction.getAmount().negate());
        repository.delete(transaction);
    }
}