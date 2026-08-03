package com.example.finance_manager.repository;

import com.example.finance_manager.entity.FinancialAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FinancialAccountRepository extends JpaRepository<FinancialAccount,Long> {
    @Query("""
            select a
            from FinancialAccount a
            left join fetch a.transactions
            where a.id = :id
            """)
    Optional<FinancialAccount> findWithTransactionsById(Long id);
}
