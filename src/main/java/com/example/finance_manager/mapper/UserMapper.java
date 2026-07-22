package com.example.finance_manager.mapper;

import com.example.finance_manager.dto.request.UserRequest;
import com.example.finance_manager.dto.response.UserResponse;
import com.example.finance_manager.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
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
        return response;
    }
}
