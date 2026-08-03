package com.example.finance_manager.controller;

import com.example.finance_manager.dto.request.UserRequest;
import com.example.finance_manager.dto.response.UserResponse;
import com.example.finance_manager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(
        name = "Users",
        description = "Operations for managing application users"
)
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @Operation(summary = "Get all users")
    @GetMapping
    public List<UserResponse> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id){
        return service.geResponseById(id);
    }

    @Operation(summary = "Create user")
    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest request){
        return service.create(request);
    }

    @Operation(summary = "Update user by id")
    @PutMapping("/{id}")
    public UserResponse update(@Valid @PathVariable Long id, @RequestBody UserRequest request) {return service.update(id, request);}

    @Operation(summary = "Delete user")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
