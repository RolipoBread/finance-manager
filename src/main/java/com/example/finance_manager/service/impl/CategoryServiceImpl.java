package com.example.finance_manager.service.impl;

import com.example.finance_manager.dto.request.CategoryRequest;
import com.example.finance_manager.dto.response.CategoryResponse;
import com.example.finance_manager.entity.Category;
import com.example.finance_manager.mapper.CategoryMapper;
import com.example.finance_manager.repository.CategoryRepository;
import com.example.finance_manager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public List<CategoryResponse> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public Category getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public CategoryResponse getResponseById(Long id) {
        return mapper.toResponse(getById(id));
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        Category category = mapper.toEntity(request);
        Category saved = repository.save(category);
        return mapper.toResponse(saved);
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = getById(id);
        category.setName(request.getName());
        Category saved = repository.save(category);
        return mapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        Category category = getById(id);
        repository.delete(category);
    }
}
