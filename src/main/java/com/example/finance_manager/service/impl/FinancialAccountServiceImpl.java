package com.example.finance_manager.service.impl;

import com.example.finance_manager.dto.request.FinancialAccountRequest;
import com.example.finance_manager.dto.response.FinancialAccountResponse;
import com.example.finance_manager.entity.FinancialAccount;
import com.example.finance_manager.entity.User;
import com.example.finance_manager.mapper.FinancialAccountMapper;
import com.example.finance_manager.repository.FinancialAccountRepository;
import com.example.finance_manager.repository.UserRepository;
import com.example.finance_manager.service.FinancialAccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialAccountServiceImpl implements FinancialAccountService {

    private final FinancialAccountRepository repository;
    private final FinancialAccountMapper mapper;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public void updateBalance(Long id, BigDecimal amount) {
        FinancialAccount account = getById(id);
        account.setBalance(account.getBalance().add(amount));
        repository.save(account);
    }


    @Override
    @Transactional
    public FinancialAccountResponse create(FinancialAccountRequest request) {
        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        FinancialAccount account = mapper.toEntity(request);
        account.setOwner(owner);
        FinancialAccount saved = repository.save(account);
        return mapper.toResponse(saved);
    }


    @Override
    public List<FinancialAccountResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }


    @Override
    public FinancialAccount getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }


    @Override
    public FinancialAccountResponse getResponseById(Long id) {
        return mapper.toResponse(getById(id));
    }


    @Override
    @Transactional
    public FinancialAccountResponse update(Long id, FinancialAccountRequest request) {
        FinancialAccount account = getById(id);
        account.setName(request.getName());

        if (!account.getOwner().getId().equals(request.getOwnerId())) {
            User owner = userRepository.findById(request.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            account.setOwner(owner);
        }
        FinancialAccount saved = repository.save(account);
        return mapper.toResponse(saved);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        FinancialAccount account = getById(id);
        repository.delete(account);
    }
}