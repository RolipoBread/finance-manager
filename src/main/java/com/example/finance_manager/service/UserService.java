package com.example.finance_manager.service;


import com.example.finance_manager.dto.request.UserRequest;
import com.example.finance_manager.dto.response.UserResponse;
import com.example.finance_manager.entity.User;

import java.util.List;

public interface UserService {
    UserResponse create(UserRequest request);
    User getById(Long id);
    List<UserResponse> getAll();
    UserResponse update(Long id, UserRequest request);
    void delete(Long id);
}
