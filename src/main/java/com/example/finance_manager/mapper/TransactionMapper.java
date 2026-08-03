package com.example.finance_manager.mapper;

import com.example.finance_manager.dto.request.TransactionRequest;
import com.example.finance_manager.dto.response.TransactionResponse;
import com.example.finance_manager.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public Transaction toEntity(TransactionRequest request) {
        Transaction transaction = new Transaction();

        transaction.setName(request.getName());
        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setDate(request.getDate());

        return transaction;
    }

    public TransactionResponse toResponse(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();

        response.setId(transaction.getId());
        response.setName(transaction.getName());
        response.setDescription(transaction.getDescription());
        response.setAmount(transaction.getAmount());
        response.setDate(transaction.getDate());

        if (transaction.getCategory() != null) {
            response.setCategoryId(transaction.getCategory().getId());
            response.setCategoryName(transaction.getCategory().getName());
        }

        if (transaction.getAccount() != null) {
            response.setAccountId(transaction.getAccount().getId());
        }

        return response;
    }
}