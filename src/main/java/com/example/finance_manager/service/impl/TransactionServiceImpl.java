package com.example.finance_manager.service.impl;

import com.example.finance_manager.dto.filter.TransactionFilter;
import com.example.finance_manager.dto.request.TransactionRequest;
import com.example.finance_manager.dto.response.TransactionResponse;
import com.example.finance_manager.dto.filter.DateRange;
import com.example.finance_manager.entity.FinancialAccount;
import com.example.finance_manager.entity.Transaction;
import com.example.finance_manager.mapper.TransactionMapper;
import com.example.finance_manager.repository.TransactionRepository;
import com.example.finance_manager.repository.specification.TransactionSpecification;
import com.example.finance_manager.service.CategoryService;
import com.example.finance_manager.service.DateRangeResolver;
import com.example.finance_manager.service.FinancialAccountService;
import com.example.finance_manager.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    private final CategoryService categoryService;
    private final FinancialAccountService accountService;

    private final DateRangeResolver dateRangeResolver;

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
    public Page<TransactionResponse> getAll(
            TransactionFilter filter,
            Pageable pageable
    ) {

        DateRange range = dateRangeResolver.resolve(filter);

        if (range != null) {
            filter.setFrom(range.getFrom());
            filter.setTo(range.getTo());
        }

        return repository
                .findAll(
                        TransactionSpecification.build(filter),
                        pageable
                )
                .map(mapper::toResponse);
    }

    @Override
    public  Page<TransactionResponse> getByAccount(Long accointId, Pageable pageable){
        accountService.getById(accointId);
        return repository.findByAccountId(accointId,pageable).map(mapper::toResponse);
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