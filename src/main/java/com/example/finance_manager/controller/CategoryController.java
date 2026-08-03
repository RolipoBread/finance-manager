package com.example.finance_manager.controller;

import com.example.finance_manager.dto.request.CategoryRequest;
import com.example.finance_manager.dto.response.CategoryResponse;
import com.example.finance_manager.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Categories",
        description = "Operations for transaction categories"
)
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @Operation(summary = "Get all categories")
    @GetMapping
    public List<CategoryResponse> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get category by id")
    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id) {
        return service.getResponseById(id);
    }

    @Operation(summary = "Create category by id")
    @PostMapping
    public CategoryResponse create(@Valid @RequestBody CategoryRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Update category by id")
    @PutMapping("/{id}")
    public CategoryResponse update(@Valid @PathVariable Long id, @RequestBody CategoryRequest request) {return service.update(id, request);}

    @Operation(summary = "Delete category by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}