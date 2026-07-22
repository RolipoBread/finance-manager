package com.example.finance_manager.mapper;

import com.example.finance_manager.dto.request.CategoryRequest;
import com.example.finance_manager.dto.response.CategoryResponse;
import com.example.finance_manager.dto.response.UserResponse;
import com.example.finance_manager.entity.Category;
import com.example.finance_manager.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity (CategoryRequest response){
        Category category = new Category();
        category.setName(response.getName());
        return category;
    }

    public CategoryResponse toResponse (Category category){
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }
}
