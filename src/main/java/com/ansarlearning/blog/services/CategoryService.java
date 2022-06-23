package com.ansarlearning.blog.services;

import java.util.List;

import com.ansarlearning.blog.payload.CategoryDto;

public interface CategoryService {

	public CategoryDto createCategory(CategoryDto categoryDto);

	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	public CategoryDto getCategoryById(Integer categoryID);

	public List<CategoryDto> getAllCategory();

	public void deleteCategory(Integer categoryId);
}
