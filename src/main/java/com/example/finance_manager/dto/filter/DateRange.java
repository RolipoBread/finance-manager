package com.example.finance_manager.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class DateRange {
    private LocalDate from;
    private LocalDate to;

}
