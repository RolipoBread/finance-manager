package com.example.finance_manager.service;

import com.example.finance_manager.dto.request.CategoryRequest;
import com.example.finance_manager.dto.response.CategoryResponse;
import com.example.finance_manager.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();
    Category getById(Long id);
    CategoryResponse getResponseById(Long id);
    CategoryResponse create(CategoryRequest request);
    CategoryResponse update(Long id,CategoryRequest request);
    void delete(Long id);
}
