package com.example.finance_manager.service;

import com.example.finance_manager.dto.filter.TransactionFilter;
import com.example.finance_manager.dto.request.TransactionRequest;
import com.example.finance_manager.dto.response.TransactionResponse;
import com.example.finance_manager.entity.Transaction;
import com.example.finance_manager.entity.TransactionPeriod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface TransactionService {
    TransactionResponse create(TransactionRequest request);
    Page<TransactionResponse> getAll(TransactionFilter filter, Pageable pageable);
    Page<TransactionResponse> getByAccount(Long accoingId, Pageable pageable);
    Transaction getById(Long id);
    TransactionResponse getResponseById(Long id);
    TransactionResponse update(Long id, TransactionRequest request);
    void delete(Long id);


}
