package com.example.finance_manager.repository;

import com.example.finance_manager.dto.response.CategoryAmountResponse;
import com.example.finance_manager.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    Page<Transaction> findByAccountId(Long accountId, Pageable pageable);
    @Query("""
    SELECT new com.example.finance_manager.dto.response.CategoryAmountResponse(
        c.name,
        SUM(t.amount)
    )
    FROM Transaction t
    JOIN t.category c
    JOIN t.account a
    WHERE a.owner.id = :userId
    AND t.amount < 0
    GROUP BY c.name
""")
    List<CategoryAmountResponse> findExpensesByCategory(Long userId);

    @Query("""
    SELECT new com.example.finance_manager.dto.response.CategoryAmountResponse(
        c.name,
        SUM(t.amount)
    )
    FROM Transaction t
    JOIN t.category c
    JOIN t.account a
    WHERE a.owner.id = :userId
    AND t.amount > 0
    GROUP BY c.name
    """)
    List<CategoryAmountResponse> findIncomeByCategory(Long userId);
    Page<Transaction> findByDateBetween(LocalDate from, LocalDate to, Pageable pageable);
}
