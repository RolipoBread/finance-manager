package com.example.finance_manager.service;

import com.example.finance_manager.dto.request.TransactionRequest;
import com.example.finance_manager.dto.response.TransactionResponse;
import com.example.finance_manager.entity.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionResponse create(TransactionRequest request);
    List<TransactionResponse> getAll();
    Transaction getById(Long id);
    TransactionResponse getResponseById(Long id);
    TransactionResponse update(Long id, TransactionRequest request);
    void delete(Long id);

}
