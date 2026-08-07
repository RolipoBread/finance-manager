package com.example.finance_manager.entity;

import java.time.LocalDate;

public record DateRange(
        LocalDate from,
        LocalDate to
) {}
