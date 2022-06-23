package com.ansarlearning.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ansarlearning.blog.entity.Categories;
import com.ansarlearning.blog.exception.ResourceNotFoundException;
import com.ansarlearning.blog.payload.CategoryDto;
import com.ansarlearning.blog.repository.CategoryRepo;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	public ModelMapper mapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Categories cat = this.mapper.map(categoryDto, Categories.class);
		Categories saveCategory = categoryRepo.save(cat);

		return this.mapper.map(saveCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Categories categoriesById = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "CategoryId", categoryId));
		categoriesById.setCategoryTitle(categoryDto.getCategoryTitle());
		categoriesById.setCategoryDescription(categoryDto.getCategoryDescription());
		this.categoryRepo.save(categoriesById);
		return this.mapper.map(categoriesById, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryID) {
		Categories categoriesById = this.categoryRepo.findById(categoryID)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category", categoryID));

		return this.mapper.map(categoriesById, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Categories> findAll = this.categoryRepo.findAll();
		List<CategoryDto> categoryDtoList = findAll.stream().map(cat -> this.mapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		return categoryDtoList;
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Categories categoriesById = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category", categoryId));
		this.categoryRepo.delete(categoriesById);

	}

}
