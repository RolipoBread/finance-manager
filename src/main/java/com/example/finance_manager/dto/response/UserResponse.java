package com.example.finance_manager.dto.response;

import com.example.finance_manager.dto.request.FinancialAccountRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String login;

    private List<FinancialAccountResponse> accounts;
}
