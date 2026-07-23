package com.example.finance_manager.service.impl;

import com.example.finance_manager.dto.request.UserRequest;
import com.example.finance_manager.dto.response.UserResponse;
import com.example.finance_manager.mapper.UserMapper;
import com.example.finance_manager.service.UserService;
import com.example.finance_manager.entity.User;
import com.example.finance_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public User getById(Long id){
        return repository.findById(id).orElseThrow(() ->new RuntimeException("User not found"));
    }

    @Override
    public List<UserResponse> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public UserResponse create(UserRequest request){
        User user = mapper.toEntity(request);
        User saved = repository.save(user);
        return mapper.toResponse(saved);
    }

    @Override
    public void delete(Long id){
        User user = getById(id);
        repository.delete(user);
    }

    @Override
    public UserResponse update(Long id, UserRequest user){
        User exist = getById(id);
        exist.setName(user.getName());
        exist.setLogin(user.getLogin());
        exist.setPassword(user.getPassword());
        User saved = repository.save(exist);
        return mapper.toResponse(saved);
    }

}
