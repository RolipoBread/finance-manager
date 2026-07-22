package com.example.finance_manager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    public String name;
    public String login;
    public String password;
    public List<FinancialAccountRequest> accounts;
}
