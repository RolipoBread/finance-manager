package com.example.finance_manager.mapper;

import com.example.finance_manager.dto.request.TransactionRequest;
import com.example.finance_manager.dto.response.TransactionResponse;
import com.example.finance_manager.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public Transaction toEntity(TransactionRequest response){
        Transaction transaction = new Transaction();
        transaction.setName(response.getName());
        transaction.setBill(response.getBill());
        transaction.setDate(response.getDate());
        transaction.setCategory(response.getCategory());
        return transaction;
    }

    public TransactionResponse toResponse (Transaction transaction){
        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        response.setName(transaction.getName());
        response.setBill(transaction.getBill());
        response.setDate(transaction.getDate());
        response.setCategory(transaction.getCategory());
        return response;
    }
}
