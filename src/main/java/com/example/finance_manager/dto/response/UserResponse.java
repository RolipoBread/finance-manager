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
    public Long id;
    public String name;
    public String login;
    public String password;
    public List<FinancialAccountRequest> accounts;
}
