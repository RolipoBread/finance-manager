package com.example.finance_manager.controller;

import com.example.finance_manager.dto.request.UserRequest;
import com.example.finance_manager.dto.response.UserResponse;
import com.example.finance_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserResponse getById(Long id){
        return service.geResponseById(id);
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest request){
        return service.create(request);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id,
                               @RequestBody UserRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
