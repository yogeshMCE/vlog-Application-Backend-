package com.app.vlog.Services;

import com.app.vlog.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer category_id );

    //delete
    public void deleteCategory(Integer category_id);

    //get by id
    CategoryDto getCategoryById(Integer category_id);

    // get all
    List<CategoryDto> getAllCategories();
}
