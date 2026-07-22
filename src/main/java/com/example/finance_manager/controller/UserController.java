package com.example.finance_manager.controller;

import com.example.finance_manager.dto.request.UserRequest;
import com.example.finance_manager.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")

public class UserController {

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest dto){

    }
}
