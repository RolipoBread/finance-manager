package com.example.finance_manager.mapper;

import com.example.finance_manager.dto.request.FinancialAccountRequest;
import com.example.finance_manager.dto.response.FinancialAccountResponse;
import com.example.finance_manager.entity.FinancialAccount;
import org.springframework.stereotype.Component;

@Component
public class FinancialAccountMapper {
    public FinancialAccount toEntity (FinancialAccountRequest response){
        FinancialAccount account = new FinancialAccount();
        account.setName(response.getName());
        account.setBalance(response.getBalance());
        account.setOwner(response.getOwner());
        account.setTransactions(response.getTransactions());
        return account;
    }

    public FinancialAccountResponse toResponse (FinancialAccount account){
        FinancialAccountResponse response = new FinancialAccountResponse();
        response.setId(account.getId());
        response.setName(account.getName());
        response.setBalance(account.getBalance());
        response.setOwner(account.getOwner());
        response.setTransactions(account.getTransactions());
        return response;
    }
}
