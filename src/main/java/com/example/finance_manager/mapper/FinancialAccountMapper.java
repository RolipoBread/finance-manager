package com.example.finance_manager.mapper;

import com.example.finance_manager.dto.request.FinancialAccountRequest;
import com.example.finance_manager.dto.response.FinancialAccountResponse;
import com.example.finance_manager.entity.FinancialAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinancialAccountMapper {

    private final TransactionMapper transactionMapper;

    public FinancialAccount toEntity (FinancialAccountRequest response){
        FinancialAccount account = new FinancialAccount();
        account.setName(response.getName());
        account.setBalance(response.getBalance());

        return account;
    }

    public FinancialAccountResponse toResponse (FinancialAccount account){
        FinancialAccountResponse response = new FinancialAccountResponse();
        response.setId(account.getId());
        response.setName(account.getName());
        response.setBalance(account.getBalance());
        response.setOwnerId(account.getOwner().getId());
        if(account.getTransactions()!=null){
            response.setTransactions(
                    account.getTransactions()
                            .stream()
                            .map(transactionMapper::toResponse)
                            .toList()
            );
        }
        return response;
    }
}
