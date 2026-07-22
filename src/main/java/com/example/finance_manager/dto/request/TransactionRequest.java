package com.example.finance_manager.dto.request;

import com.example.finance_manager.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    public String name;
    public Double bill;
    public LocalDate date;
    public Category category;
}
