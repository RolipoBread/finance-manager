package com.example.finance_manager.repository.specification;

import com.example.finance_manager.dto.filter.TransactionFilter;
import com.example.finance_manager.entity.Transaction;
import com.example.finance_manager.entity.TransactionType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public final class TransactionSpecification {

    private TransactionSpecification() {
    }

    public static Specification<Transaction> build(TransactionFilter filter) {

        Specification<Transaction> specification =
                (root, query, cb) -> cb.conjunction();

        // Фильтр по пользователю
        if (filter.getUserId() != null) {
            specification = specification.and((root, query, cb) ->
                    cb.equal(
                            root.join("account")
                                    .join("owner")
                                    .get("id"),
                            filter.getUserId()
                    )
            );
        }

        // Фильтр по конкретному счёту
        if (filter.getAccountId() != null) {
            specification = specification.and((root, query, cb) ->
                    cb.equal(
                            root.get("account").get("id"),
                            filter.getAccountId()
                    )
            );
        }

        // Фильтр по типу транзакции
        if (filter.getType() == TransactionType.INCOME) {
            specification = specification.and((root, query, cb) ->
                    cb.greaterThan(
                            root.get("amount"),
                            BigDecimal.ZERO
                    )
            );
        }

        if (filter.getType() == TransactionType.EXPENSE) {
            specification = specification.and((root, query, cb) ->
                    cb.lessThan(
                            root.get("amount"),
                            BigDecimal.ZERO
                    )
            );
        }

        // Фильтр "от"
        if (filter.getFrom() != null) {
            specification = specification.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(
                            root.get("date"),
                            filter.getFrom()
                    )
            );
        }

        // Фильтр "до"
        if (filter.getTo() != null) {
            specification = specification.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(
                            root.get("date"),
                            filter.getTo()
                    )
            );
        }

        return specification;
    }
}