package com.example.finance_manager.repository;

import com.example.finance_manager.entity.FinancialAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialAccountRepository extends JpaRepository<FinancialAccount,Long> {

}
