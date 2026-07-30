package com.example.finance_manager.mapper;

import com.example.finance_manager.dto.request.UserRequest;
import com.example.finance_manager.dto.response.UserResponse;
import com.example.finance_manager.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final FinancialAccountMapper accountMapper;

    public User toEntity(UserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
         return user;
    }

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setLogin(user.getLogin());

        if (user.getAccounts() != null) {
            response.setAccounts(
                    user.getAccounts()
                            .stream()
                            .map(accountMapper::toResponse)
                            .toList()
            );
        }
        return response;
    }
}
